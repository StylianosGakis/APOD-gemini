package com.stylianosgakis.mars.apod.api

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.database.ApodDao

class ApodRepository(
    private val apodService: ApodService,
    private val apodDao: ApodDao,
) {
    suspend fun getRandomApod(): Either<CallError, List<ApodItem>> {
        return apodService.getRandomApod().map { apodItems ->
            apodItems.map { item ->
                item.copy(title = item.title.trim())
            }
//        }.onRight { apodItems ->
//            apodDao.insertAll(apodItems.map { item -> item.toApodEntity() })
        }
    }
}
