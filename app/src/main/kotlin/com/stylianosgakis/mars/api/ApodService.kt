package com.stylianosgakis.mars.api

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.stylianosgakis.mars.apod.ApodItem
import retrofit2.http.GET

interface ApodService {
    @GET("planetary/apod?count=20")
    suspend fun getRandomApod(): Either<CallError, List<ApodItem>>
}
