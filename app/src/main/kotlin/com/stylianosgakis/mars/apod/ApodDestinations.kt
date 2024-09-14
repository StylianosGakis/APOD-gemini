package com.stylianosgakis.mars.apod

import kotlinx.serialization.Serializable

sealed interface ApodDestinations {
    @Serializable
    data object ApodCollection : ApodDestinations

    @Serializable
    data class ApodDetails(
        val apodTitle: String,
        val apodUrl: String?,
    ) : ApodDestinations
}
