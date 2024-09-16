package com.stylianosgakis.mars.apod.api

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.stylianosgakis.mars.apod.ApodItem
import retrofit2.http.GET

// https://api.nasa.gov/
interface ApodService {
    @GET("planetary/apod?count=20")
    suspend fun getRandomApod(): Either<CallError, List<ApodItem>>
}
