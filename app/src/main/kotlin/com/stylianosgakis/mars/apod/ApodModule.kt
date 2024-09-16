package com.stylianosgakis.mars.apod

import org.koin.dsl.module

val apodModule =
    module {
//        single<ApodService> {
//            val okHttpClient =
//                OkHttpClient
//                    .Builder()
//                    .addInterceptor(
//                        ApodAuthTokenInterceptor(get<Context>().getString(R.string.nasa_token))
//                    )
//                    .build()
//            val serialFormat = Json {
//                ignoreUnknownKeys = true
//                explicitNulls = false
//            }
//            Retrofit
//                .Builder()
//                .callFactory(okHttpClient)
//                .baseUrl("https://api.nasa.gov/")
//                .addCallAdapterFactory(EitherCallAdapterFactory.create())
//                .addConverterFactory(serialFormat.asConverterFactory("application/json".toMediaType()))
//                .build()
//                .create<ApodService>()
//        }
//        single<ApodRepository> { ApodRepository(get(), get()) }
//        viewModel<ApodCollectionViewModel> { ApodCollectionViewModel(get()) }
//        viewModel<ApodDetailsViewModel> { ApodDetailsViewModel(get(), get(), get()) }
    }
