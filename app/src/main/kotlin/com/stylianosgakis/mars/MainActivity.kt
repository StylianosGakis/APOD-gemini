package com.stylianosgakis.mars

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import com.stylianosgakis.mars.theme.MarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = isSystemInDarkTheme()
            DisposableEffect(isDarkMode) {
                setupEdgeToEdge(isDarkMode)
                onDispose {}
            }
            MarsTheme(isDarkMode, false) {
                App()
            }
        }
    }

    private fun setupEdgeToEdge(isDarkMode: Boolean? = null) {
        enableEdgeToEdge(
            statusBarStyle =
                when (isDarkMode) {
                    true -> SystemBarStyle.dark(Color.TRANSPARENT)
                    false -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                    null -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
                },
            navigationBarStyle =
                when (isDarkMode) {
                    true -> SystemBarStyle.dark(Color.TRANSPARENT)
                    false -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                    null -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
                },
        )
    }
}
