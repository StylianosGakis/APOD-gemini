package com.stylianosgakis.mars

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stylianosgakis.mars.apod.ApodDestinations
import com.stylianosgakis.mars.picture.PictureDestinations

@Composable
fun App(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        val navController = rememberNavController()
        val density = LocalDensity.current
        NavHost(
            navController = navController,
            startDestination = ApodDestinations.ApodCollection::class,
//            enterTransition = { MotionDefaults.sharedXAxisEnter(density) },
//            exitTransition = { MotionDefaults.sharedXAxisExit(density) },
//            popEnterTransition = { MotionDefaults.sharedXAxisPopEnter(density) },
//            popExitTransition = { MotionDefaults.sharedXAxisPopExit(density) },
        ) {
            composable<ApodDestinations.ApodCollection> {
//                ApodCollectionDestination(
//                    apodCollectionViewModel = koinViewModel(),
//                    onNavigateToApodDetails = { title, url ->
//                        navController.navigate(
//                            ApodDestinations.ApodDetails(
//                                apodTitle = title,
//                                apodUrl = url,
//                            )
//                        )
//                    }
//                )
            }
            composable<ApodDestinations.ApodDetails> { navBackStackEntry ->
//                val route = navBackStackEntry.toRoute<ApodDestinations.ApodDetails>()
//                ApodDetailsDestination(
//                    koinViewModel(),
//                    route.apodTitle,
//                    route.apodUrl,
//                    { navController.navigate(PictureDestinations.Picture(it)) }
//                )
            }
            composable<PictureDestinations.Picture> { navBackStackEntry ->
//                val url = navBackStackEntry.toRoute<PictureDestinations.Picture>().url
//                PictureDestination(url)
            }
        }
    }
}

//@Composable
//fun AppDone(modifier: Modifier = Modifier) {
//    SharedTransitionLayout {
//        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
//            Surface(
//                color = MaterialTheme.colorScheme.background,
//                modifier = modifier.fillMaxSize(),
//            ) {
//                val navController = rememberNavController()
//                val density = LocalDensity.current
//                NavHost(
//                    navController = navController,
//                    startDestination = ApodDestinations.ApodCollection::class,
//                    enterTransition = { MotionDefaults.sharedXAxisEnter(density) },
//                    exitTransition = { MotionDefaults.sharedXAxisExit(density) },
//                    popEnterTransition = { MotionDefaults.sharedXAxisPopEnter(density) },
//                    popExitTransition = { MotionDefaults.sharedXAxisPopExit(density) },
//                ) {
//                    composable<ApodDestinations.ApodCollection> {
//                        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
//                            ApodCollectionDestination(
//                                apodCollectionViewModel = koinViewModel(),
//                                onNavigateToApodDetails = { title, url ->
//                                    navController.navigate(
//                                        ApodDestinations.ApodDetails(
//                                            apodTitle = title,
//                                            apodUrl = url,
//                                        )
//                                    )
//                                }
//                            )
//                        }
//                    }
//                    composable<ApodDestinations.ApodDetails> { navBackStackEntry ->
//                        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
//                            val route = navBackStackEntry.toRoute<ApodDestinations.ApodDetails>()
//                            ApodDetailsDestination(
//                                koinViewModel(),
//                                route.apodTitle,
//                                route.apodUrl,
//                                { navController.navigate(PictureDestinations.Picture(it)) }
//                            )
//                        }
//                    }
//                    composable<PictureDestinations.Picture> { navBackStackEntry ->
//                        val url = navBackStackEntry.toRoute<PictureDestinations.Picture>().url
//                        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
//                            PictureDestination(url)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//val LocalSharedTransitionScope: ProvidableCompositionLocal<SharedTransitionScope?> =
//    compositionLocalOf { null }
//val LocalAnimatedContentScope: ProvidableCompositionLocal<AnimatedContentScope?> =
//    compositionLocalOf { null }