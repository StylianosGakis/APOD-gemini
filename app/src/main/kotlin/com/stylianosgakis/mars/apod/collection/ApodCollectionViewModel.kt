package com.stylianosgakis.mars.apod.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.api.ApodRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class ApodCollectionViewModel(
    private val apodRepository: ApodRepository,
) : ViewModel() {
    private val refreshSignal = Channel<Unit>(Channel.UNLIMITED)
    private var lastEmittedValue: ApodCollectionUiState? = null
    val uiState: StateFlow<ApodCollectionUiState> = flow {
        if (lastEmittedValue !is ApodCollectionUiState.Data) {
            emit(fetchApodItems())
        }
        emitAll(
            refreshSignal.receiveAsFlow().transformLatest {
                emit(ApodCollectionUiState.Loading)
                emit(fetchApodItems())
            }
        )
    }.onEach {
        lastEmittedValue = it
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        ApodCollectionUiState.Loading
    )

    private suspend fun fetchApodItems() = when (val response = apodRepository.getRandomApod()) {
        is Either.Left -> {
            Log.e("NASA", "getRandomApod failed with:${response.value}")
            ApodCollectionUiState.Error
        }

        is Either.Right -> {
            ApodCollectionUiState.Data(response.value)
        }
    }

    fun refreshPhotos() {
        refreshSignal.trySend(Unit)
    }
}

sealed interface ApodCollectionUiState {
    data object Loading : ApodCollectionUiState
    data object Error : ApodCollectionUiState
    data class Data(val apodItems: List<ApodItem>) : ApodCollectionUiState
}