package com.stylianosgakis.mars.gemini

import okhttp3.Interceptor
import okhttp3.Response

class GeminiAuthTokenInterceptor(
    private val apiKey: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithAuthToken = chain.request().url.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()
        return chain.proceed(
            chain.request().newBuilder().url(urlWithAuthToken).build()
        )
    }
}