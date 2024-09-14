package com.stylianosgakis.mars.apod.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import arrow.core.Either
import com.stylianosgakis.mars.apod.ApodDestinations
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.database.ApodDao
import com.stylianosgakis.mars.database.toApodItem
import com.stylianosgakis.mars.gemini.GeminiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ApodDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    apodDao: ApodDao,
    private val geminiRepository: GeminiRepository
) : ViewModel() {
    private val apodId = savedStateHandle.toRoute<ApodDestinations.ApodDetails>().apodTitle
    private val geminiResponseState: MutableStateFlow<GeminiResponseState> =
        MutableStateFlow(GeminiResponseState.Idle)
    val uiState = combine(
        apodDao.getApod(apodId),
        geminiResponseState
    ) { apodItem, geminiResponseState ->
        if (apodItem == null) {
            ApodDetailsUiState.NotFound
        } else {
            ApodDetailsUiState.Data(apodItem.toApodItem(), geminiResponseState)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        ApodDetailsUiState.Loading
    )

    fun generateGeminiInformation() {
        viewModelScope.launch {
            when (geminiResponseState.value) {
                GeminiResponseState.Loading -> {}
                GeminiResponseState.Idle,
                GeminiResponseState.Error,
                is GeminiResponseState.Data -> {
                    geminiResponseState.value = GeminiResponseState.Loading
                    val geminiInput = (uiState.value as ApodDetailsUiState.Data)
                        .apodItem
                        .let { apodItem ->
                            """
                                Title: ${apodItem.title}
                                Explanation: ${apodItem.explanation}
                            """.trimIndent()
                        }
                    when (val generatedResponse = geminiRepository.generateResponse(geminiInput)) {
                        is Either.Left -> geminiResponseState.value = GeminiResponseState.Error
                        is Either.Right -> {
                            geminiResponseState.value =
                                GeminiResponseState.Data(generatedResponse.value)
                        }
                    }
                }
            }
        }
    }
}

sealed interface ApodDetailsUiState {
    data object Loading : ApodDetailsUiState
    data object NotFound : ApodDetailsUiState
    data class Data(
        val apodItem: ApodItem,
        val geminiResponseState: GeminiResponseState
    ) : ApodDetailsUiState
}

sealed interface GeminiResponseState {
    data object Idle : GeminiResponseState
    data object Loading : GeminiResponseState
    data object Error : GeminiResponseState
    data class Data(val response: String) : GeminiResponseState
}