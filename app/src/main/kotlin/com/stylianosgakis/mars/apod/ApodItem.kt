package com.stylianosgakis.mars.apod

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApodItem(
    val title: String,
    val copyright: String?,
    val date: LocalDate,
    val explanation: String,
    val url: String?,
    @SerialName("media_type")
    val mediaType: String,
)