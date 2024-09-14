package com.stylianosgakis.mars.picture

import kotlinx.serialization.Serializable

sealed interface PictureDestinations {
    @Serializable
    data class Picture(val url: String) : PictureDestinations
}