package com.stylianosgakis.mars

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import com.stylianosgakis.mars.apod.apodModule
import com.stylianosgakis.mars.database.databaseModule
import com.stylianosgakis.mars.gemini.geminiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarsApp : Application()/*, ImageLoaderFactory*/ {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarsApp)
            modules(apodModule, databaseModule, geminiModule)
        }
    }

//    /**
//     * https://github.com/coil-kt/coil/blob/ce14e14bc9a25c6952e26e248aad75fbd2e9f9d0/coil-singleton/src/main/java/coil/Coil.kt#L66-L79
//     */
//    override fun newImageLoader(): ImageLoader {
//        return ImageLoader.Builder(this)
//            .memoryCache { MemoryCache.Builder(this).build() }
//            .build()
//    }
}
