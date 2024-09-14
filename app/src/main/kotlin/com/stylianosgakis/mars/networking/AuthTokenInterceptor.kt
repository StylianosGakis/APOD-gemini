package com.stylianosgakis.mars.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(
    private val apiKey: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithAuthToken = chain.request().url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        return chain.proceed(
            chain.request().newBuilder().url(urlWithAuthToken).build()
        )
    }
}