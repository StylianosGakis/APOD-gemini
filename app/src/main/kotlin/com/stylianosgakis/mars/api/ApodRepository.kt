package com.stylianosgakis.mars.api

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.database.ApodDao
import com.stylianosgakis.mars.database.toApodEntity

class ApodRepository(
    private val apodService: ApodService,
    private val apodDao: ApodDao,
) {
    suspend fun getRandomApod(): Either<CallError, List<ApodItem>> =
        apodService.getRandomApod().map { apodItems ->
            apodItems.map { item ->
                item.copy(title = item.title.trim())
            }
        }.onRight { apodItems ->
            apodDao.insertAll(apodItems.map { item -> item.toApodEntity() })
        }
}
