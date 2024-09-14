package com.stylianosgakis.mars.gemini

import android.content.Context
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.stylianosgakis.mars.R
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val geminiModule = module {
    single<GeminiService> {
        val okHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(
                    GeminiAuthTokenInterceptor(get<Context>().getString(R.string.gemini_token))
                )
                .build()
        val serialFormat = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        Retrofit
            .Builder()
            .callFactory(okHttpClient)
            .baseUrl("https://generativelanguage.googleapis.com/")
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .addConverterFactory(serialFormat.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GeminiService::class.java)
    }
    single<GeminiRepository> { GeminiRepository(get()) }
}