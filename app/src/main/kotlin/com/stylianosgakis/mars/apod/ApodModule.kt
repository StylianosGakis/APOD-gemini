package com.stylianosgakis.mars.apod

import android.content.Context
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.stylianosgakis.mars.R
import com.stylianosgakis.mars.api.ApodRepository
import com.stylianosgakis.mars.api.ApodService
import com.stylianosgakis.mars.apod.collection.ApodCollectionViewModel
import com.stylianosgakis.mars.apod.details.ApodDetailsViewModel
import com.stylianosgakis.mars.networking.AuthTokenInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val apodModule =
    module {
        single<ApodService> {
            val okHttpClient =
                OkHttpClient
                    .Builder()
                    .addInterceptor(AuthTokenInterceptor(get<Context>().getString(R.string.nasa_token)))
                    .build()
            val serialFormat = Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            Retrofit
                .Builder()
                .callFactory(okHttpClient)
                .baseUrl("https://api.nasa.gov/")
                .addCallAdapterFactory(EitherCallAdapterFactory.create())
                .addConverterFactory(serialFormat.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(ApodService::class.java)
        }
        single<ApodRepository> { ApodRepository(get(), get()) }
        viewModel<ApodCollectionViewModel> { ApodCollectionViewModel(get()) }
        viewModel<ApodDetailsViewModel> { ApodDetailsViewModel(get(), get()) }
    }
