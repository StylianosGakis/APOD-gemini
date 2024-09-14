package com.stylianosgakis.mars

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.stylianosgakis.mars.apod.ApodDestinations
import com.stylianosgakis.mars.apod.collection.ApodCollectionDestination
import com.stylianosgakis.mars.apod.details.ApodDetailsDestination
import com.stylianosgakis.mars.picture.PictureDestination
import com.stylianosgakis.mars.picture.PictureDestinations
import org.koin.androidx.compose.koinViewModel

@Composable
fun App(modifier: Modifier = Modifier) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = modifier.fillMaxSize(),
            ) {
                val navController = rememberNavController()
                val density = LocalDensity.current
                NavHost(
                    navController = navController,
                    startDestination = ApodDestinations.ApodCollection::class,
                    enterTransition = { MotionDefaults.sharedXAxisEnter(density) },
                    exitTransition = { MotionDefaults.sharedXAxisExit(density) },
                    popEnterTransition = { MotionDefaults.sharedXAxisPopEnter(density) },
                    popExitTransition = { MotionDefaults.sharedXAxisPopExit(density) },
                ) {
                    composable<ApodDestinations.ApodCollection> {
                        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                            ApodCollectionDestination(
                                apodCollectionViewModel = koinViewModel(),
                                onNavigateToApodDetails = { title, url ->
                                    navController.navigate(
                                        ApodDestinations.ApodDetails(
                                            apodTitle = title,
                                            apodUrl = url,
                                        )
                                    )
                                }
                            )
                        }
                    }
                    composable<ApodDestinations.ApodDetails> { navBackStackEntry ->
                        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                            val route = navBackStackEntry.toRoute<ApodDestinations.ApodDetails>()
                            ApodDetailsDestination(
                                koinViewModel(),
                                route.apodTitle,
                                route.apodUrl,
                                { navController.navigate(PictureDestinations.Picture(it)) }
                            )
                        }
                    }
                    composable<PictureDestinations.Picture> { navBackStackEntry ->
                        val url = navBackStackEntry.toRoute<PictureDestinations.Picture>().url
                        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                            PictureDestination(url)
                        }
                    }
                }
            }
        }
    }
}

val LocalSharedTransitionScope: ProvidableCompositionLocal<SharedTransitionScope> =
    compositionLocalOf { error("LocalSharedTransitionScope not provided") }
val LocalAnimatedContentScope: ProvidableCompositionLocal<AnimatedContentScope> =
    compositionLocalOf { error("LocalAnimatedContentScope not provided") }