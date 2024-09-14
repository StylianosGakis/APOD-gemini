package com.stylianosgakis.mars.apod.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.stylianosgakis.mars.apod.ApodDestinations
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.database.ApodDao
import com.stylianosgakis.mars.database.toApodItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

class ApodDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    apodDao: ApodDao,
) : ViewModel() {
    private val apodId = savedStateHandle.toRoute<ApodDestinations.ApodDetails>().apodTitle
    val uiState = apodDao.getApod(apodId).map { apodItem ->
        if (apodItem == null) {
            ApodDetailsUiState.NotFound
        } else {
            ApodDetailsUiState.Data(apodItem.toApodItem())
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        ApodDetailsUiState.Loading
    )
}

sealed interface ApodDetailsUiState {
    data object Loading : ApodDetailsUiState
    data object NotFound : ApodDetailsUiState
    data class Data(val apodItem: ApodItem) : ApodDetailsUiState
}