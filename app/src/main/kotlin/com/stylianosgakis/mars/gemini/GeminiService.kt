package com.stylianosgakis.mars.gemini

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import retrofit2.http.Body
import retrofit2.http.POST

interface GeminiService {
    @POST("v1beta/models/gemini-1.5-flash-latest:generateContent")
    suspend fun generateResponse(@Body input: GeminiInput): Either<CallError, GeminiResponse>
}