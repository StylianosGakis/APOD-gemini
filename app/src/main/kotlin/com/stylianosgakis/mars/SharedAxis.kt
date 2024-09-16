package com.stylianosgakis.mars

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

object MotionDefaults {
    fun sharedXAxisEnter(density: Density): EnterTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisEnterTransition(offsetPixels)
    }

    fun sharedXAxisExit(density: Density): ExitTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisExitTransition(-offsetPixels)
    }

    fun sharedXAxisPopEnter(density: Density): EnterTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisEnterTransition(-offsetPixels)
    }

    fun sharedXAxisPopExit(density: Density): ExitTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisExitTransition(offsetPixels)
    }
}

object SharedAxisDefaults {
    /**
     * Shared axis specs https://m3.material.io/styles/motion/transitions/transition-patterns#df9c7d76-1454-47f3-ad1c-268a31f58bad
     */
    const val SharedAxisOffset = 30.0

    private const val SharedAxisDuration = MotionTokens.DurationMedium2.toInt()

    private val sharedAxisSlideAnimationSpec = tween<IntOffset>(
        durationMillis = SharedAxisDuration,
        delayMillis = 0,
        easing = MotionTokens.EasingStandardCubicBezier,
    )

    internal fun sharedXAxisEnterTransition(initialOffsetX: Int): EnterTransition {
        val slide = slideInHorizontally(
            animationSpec = sharedAxisSlideAnimationSpec,
            initialOffsetX = { initialOffsetX },
        )
        val fade = fadeIn(
            tween(
                durationMillis = SharedAxisDuration.ForIncoming,
                delayMillis = SharedAxisDuration.ForOutgoing,
                easing = MotionTokens.EasingStandardDecelerateCubicBezier,
            ),
        )
        return slide + fade
    }

    internal fun sharedXAxisExitTransition(targetOffsetX: Int): ExitTransition {
        val slide = slideOutHorizontally(
            animationSpec = sharedAxisSlideAnimationSpec,
            targetOffsetX = { targetOffsetX },
        )
        val fade = fadeOut(
            tween(
                durationMillis = SharedAxisDuration.ForOutgoing,
                delayMillis = 0,
                easing = MotionTokens.EasingStandardAccelerateCubicBezier,
            ),
        )
        return slide + fade
    }
}

private const val ProgressThreshold = 0.3f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

/**
 * Easing info https://medium.com/androiddevelopers/easing-in-to-easing-curves-in-jetpack-compose-d72893eeeb4d
 */
private object MotionTokens {
    const val DurationMedium2 = 300.0
    val EasingStandardCubicBezier = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    val EasingStandardAccelerateCubicBezier = CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f)
    val EasingStandardDecelerateCubicBezier = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f)
}
