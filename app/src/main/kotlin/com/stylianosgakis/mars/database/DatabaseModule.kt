package com.stylianosgakis.mars.database

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        val applicationContext = get<Context>()
        Room
            .databaseBuilder<AppDatabase>(
                context = applicationContext,
                name = applicationContext.getDatabasePath("nasa_database.db").absolutePath
            )
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single<ApodDao> { get<AppDatabase>().apodDao() }
}