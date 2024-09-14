package com.stylianosgakis.mars.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stylianosgakis.mars.apod.ApodItem
import kotlinx.datetime.LocalDate

@Entity(tableName = "apod_entity")
data class ApodEntity(
    @PrimaryKey
    val title: String,
    val copyright: String?,
    val date: LocalDate,
    val explanation: String,
    val url: String?,
)

fun ApodEntity.toApodItem(): ApodItem = ApodItem(
    title = title,
    copyright = copyright,
    date = date,
    explanation = explanation,
    url = url,
)

fun ApodItem.toApodEntity(): ApodEntity = ApodEntity(
    title = title,
    copyright = copyright,
    date = date,
    explanation = explanation,
    url = url,
)