package com.stylianosgakis.mars.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.stylianosgakis.mars.theme.backgroundDark
import com.stylianosgakis.mars.theme.backgroundDarkHighContrast
import com.stylianosgakis.mars.theme.backgroundDarkMediumContrast
import com.stylianosgakis.mars.theme.backgroundLight
import com.stylianosgakis.mars.theme.backgroundLightHighContrast
import com.stylianosgakis.mars.theme.backgroundLightMediumContrast
import com.stylianosgakis.mars.theme.errorContainerDark
import com.stylianosgakis.mars.theme.errorContainerDarkHighContrast
import com.stylianosgakis.mars.theme.errorContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.errorContainerLight
import com.stylianosgakis.mars.theme.errorContainerLightHighContrast
import com.stylianosgakis.mars.theme.errorContainerLightMediumContrast
import com.stylianosgakis.mars.theme.errorDark
import com.stylianosgakis.mars.theme.errorDarkHighContrast
import com.stylianosgakis.mars.theme.errorDarkMediumContrast
import com.stylianosgakis.mars.theme.errorLight
import com.stylianosgakis.mars.theme.errorLightHighContrast
import com.stylianosgakis.mars.theme.errorLightMediumContrast
import com.stylianosgakis.mars.theme.inverseOnSurfaceDark
import com.stylianosgakis.mars.theme.inverseOnSurfaceDarkHighContrast
import com.stylianosgakis.mars.theme.inverseOnSurfaceDarkMediumContrast
import com.stylianosgakis.mars.theme.inverseOnSurfaceLight
import com.stylianosgakis.mars.theme.inverseOnSurfaceLightHighContrast
import com.stylianosgakis.mars.theme.inverseOnSurfaceLightMediumContrast
import com.stylianosgakis.mars.theme.inversePrimaryDark
import com.stylianosgakis.mars.theme.inversePrimaryDarkHighContrast
import com.stylianosgakis.mars.theme.inversePrimaryDarkMediumContrast
import com.stylianosgakis.mars.theme.inversePrimaryLight
import com.stylianosgakis.mars.theme.inversePrimaryLightHighContrast
import com.stylianosgakis.mars.theme.inversePrimaryLightMediumContrast
import com.stylianosgakis.mars.theme.inverseSurfaceDark
import com.stylianosgakis.mars.theme.inverseSurfaceDarkHighContrast
import com.stylianosgakis.mars.theme.inverseSurfaceDarkMediumContrast
import com.stylianosgakis.mars.theme.inverseSurfaceLight
import com.stylianosgakis.mars.theme.inverseSurfaceLightHighContrast
import com.stylianosgakis.mars.theme.inverseSurfaceLightMediumContrast
import com.stylianosgakis.mars.theme.onBackgroundDark
import com.stylianosgakis.mars.theme.onBackgroundDarkHighContrast
import com.stylianosgakis.mars.theme.onBackgroundDarkMediumContrast
import com.stylianosgakis.mars.theme.onBackgroundLight
import com.stylianosgakis.mars.theme.onBackgroundLightHighContrast
import com.stylianosgakis.mars.theme.onBackgroundLightMediumContrast
import com.stylianosgakis.mars.theme.onErrorContainerDark
import com.stylianosgakis.mars.theme.onErrorContainerDarkHighContrast
import com.stylianosgakis.mars.theme.onErrorContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.onErrorContainerLight
import com.stylianosgakis.mars.theme.onErrorContainerLightHighContrast
import com.stylianosgakis.mars.theme.onErrorContainerLightMediumContrast
import com.stylianosgakis.mars.theme.onErrorDark
import com.stylianosgakis.mars.theme.onErrorDarkHighContrast
import com.stylianosgakis.mars.theme.onErrorDarkMediumContrast
import com.stylianosgakis.mars.theme.onErrorLight
import com.stylianosgakis.mars.theme.onErrorLightHighContrast
import com.stylianosgakis.mars.theme.onErrorLightMediumContrast
import com.stylianosgakis.mars.theme.onPrimaryContainerDark
import com.stylianosgakis.mars.theme.onPrimaryContainerDarkHighContrast
import com.stylianosgakis.mars.theme.onPrimaryContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.onPrimaryContainerLight
import com.stylianosgakis.mars.theme.onPrimaryContainerLightHighContrast
import com.stylianosgakis.mars.theme.onPrimaryContainerLightMediumContrast
import com.stylianosgakis.mars.theme.onPrimaryDark
import com.stylianosgakis.mars.theme.onPrimaryDarkHighContrast
import com.stylianosgakis.mars.theme.onPrimaryDarkMediumContrast
import com.stylianosgakis.mars.theme.onPrimaryLight
import com.stylianosgakis.mars.theme.onPrimaryLightHighContrast
import com.stylianosgakis.mars.theme.onPrimaryLightMediumContrast
import com.stylianosgakis.mars.theme.onSecondaryContainerDark
import com.stylianosgakis.mars.theme.onSecondaryContainerDarkHighContrast
import com.stylianosgakis.mars.theme.onSecondaryContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.onSecondaryContainerLight
import com.stylianosgakis.mars.theme.onSecondaryContainerLightHighContrast
import com.stylianosgakis.mars.theme.onSecondaryContainerLightMediumContrast
import com.stylianosgakis.mars.theme.onSecondaryDark
import com.stylianosgakis.mars.theme.onSecondaryDarkHighContrast
import com.stylianosgakis.mars.theme.onSecondaryDarkMediumContrast
import com.stylianosgakis.mars.theme.onSecondaryLight
import com.stylianosgakis.mars.theme.onSecondaryLightHighContrast
import com.stylianosgakis.mars.theme.onSecondaryLightMediumContrast
import com.stylianosgakis.mars.theme.onSurfaceDark
import com.stylianosgakis.mars.theme.onSurfaceDarkHighContrast
import com.stylianosgakis.mars.theme.onSurfaceDarkMediumContrast
import com.stylianosgakis.mars.theme.onSurfaceLight
import com.stylianosgakis.mars.theme.onSurfaceLightHighContrast
import com.stylianosgakis.mars.theme.onSurfaceLightMediumContrast
import com.stylianosgakis.mars.theme.onSurfaceVariantDark
import com.stylianosgakis.mars.theme.onSurfaceVariantDarkHighContrast
import com.stylianosgakis.mars.theme.onSurfaceVariantDarkMediumContrast
import com.stylianosgakis.mars.theme.onSurfaceVariantLight
import com.stylianosgakis.mars.theme.onSurfaceVariantLightHighContrast
import com.stylianosgakis.mars.theme.onSurfaceVariantLightMediumContrast
import com.stylianosgakis.mars.theme.onTertiaryContainerDark
import com.stylianosgakis.mars.theme.onTertiaryContainerDarkHighContrast
import com.stylianosgakis.mars.theme.onTertiaryContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.onTertiaryContainerLight
import com.stylianosgakis.mars.theme.onTertiaryContainerLightHighContrast
import com.stylianosgakis.mars.theme.onTertiaryContainerLightMediumContrast
import com.stylianosgakis.mars.theme.onTertiaryDark
import com.stylianosgakis.mars.theme.onTertiaryDarkHighContrast
import com.stylianosgakis.mars.theme.onTertiaryDarkMediumContrast
import com.stylianosgakis.mars.theme.onTertiaryLight
import com.stylianosgakis.mars.theme.onTertiaryLightHighContrast
import com.stylianosgakis.mars.theme.onTertiaryLightMediumContrast
import com.stylianosgakis.mars.theme.outlineDark
import com.stylianosgakis.mars.theme.outlineDarkHighContrast
import com.stylianosgakis.mars.theme.outlineDarkMediumContrast
import com.stylianosgakis.mars.theme.outlineLight
import com.stylianosgakis.mars.theme.outlineLightHighContrast
import com.stylianosgakis.mars.theme.outlineLightMediumContrast
import com.stylianosgakis.mars.theme.outlineVariantDark
import com.stylianosgakis.mars.theme.outlineVariantDarkHighContrast
import com.stylianosgakis.mars.theme.outlineVariantDarkMediumContrast
import com.stylianosgakis.mars.theme.outlineVariantLight
import com.stylianosgakis.mars.theme.outlineVariantLightHighContrast
import com.stylianosgakis.mars.theme.outlineVariantLightMediumContrast
import com.stylianosgakis.mars.theme.primaryContainerDark
import com.stylianosgakis.mars.theme.primaryContainerDarkHighContrast
import com.stylianosgakis.mars.theme.primaryContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.primaryContainerLight
import com.stylianosgakis.mars.theme.primaryContainerLightHighContrast
import com.stylianosgakis.mars.theme.primaryContainerLightMediumContrast
import com.stylianosgakis.mars.theme.primaryDark
import com.stylianosgakis.mars.theme.primaryDarkHighContrast
import com.stylianosgakis.mars.theme.primaryDarkMediumContrast
import com.stylianosgakis.mars.theme.primaryLight
import com.stylianosgakis.mars.theme.primaryLightHighContrast
import com.stylianosgakis.mars.theme.primaryLightMediumContrast
import com.stylianosgakis.mars.theme.scrimDark
import com.stylianosgakis.mars.theme.scrimDarkHighContrast
import com.stylianosgakis.mars.theme.scrimDarkMediumContrast
import com.stylianosgakis.mars.theme.scrimLight
import com.stylianosgakis.mars.theme.scrimLightHighContrast
import com.stylianosgakis.mars.theme.scrimLightMediumContrast
import com.stylianosgakis.mars.theme.secondaryContainerDark
import com.stylianosgakis.mars.theme.secondaryContainerDarkHighContrast
import com.stylianosgakis.mars.theme.secondaryContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.secondaryContainerLight
import com.stylianosgakis.mars.theme.secondaryContainerLightHighContrast
import com.stylianosgakis.mars.theme.secondaryContainerLightMediumContrast
import com.stylianosgakis.mars.theme.secondaryDark
import com.stylianosgakis.mars.theme.secondaryDarkHighContrast
import com.stylianosgakis.mars.theme.secondaryDarkMediumContrast
import com.stylianosgakis.mars.theme.secondaryLight
import com.stylianosgakis.mars.theme.secondaryLightHighContrast
import com.stylianosgakis.mars.theme.secondaryLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceBrightDark
import com.stylianosgakis.mars.theme.surfaceBrightDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceBrightDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceBrightLight
import com.stylianosgakis.mars.theme.surfaceBrightLightHighContrast
import com.stylianosgakis.mars.theme.surfaceBrightLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerDark
import com.stylianosgakis.mars.theme.surfaceContainerDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighDark
import com.stylianosgakis.mars.theme.surfaceContainerHighDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighLight
import com.stylianosgakis.mars.theme.surfaceContainerHighLightHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighestDark
import com.stylianosgakis.mars.theme.surfaceContainerHighestDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighestDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighestLight
import com.stylianosgakis.mars.theme.surfaceContainerHighestLightHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerHighestLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerLight
import com.stylianosgakis.mars.theme.surfaceContainerLightHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowDark
import com.stylianosgakis.mars.theme.surfaceContainerLowDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowLight
import com.stylianosgakis.mars.theme.surfaceContainerLowLightHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowestDark
import com.stylianosgakis.mars.theme.surfaceContainerLowestDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowestDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowestLight
import com.stylianosgakis.mars.theme.surfaceContainerLowestLightHighContrast
import com.stylianosgakis.mars.theme.surfaceContainerLowestLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceDark
import com.stylianosgakis.mars.theme.surfaceDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceDimDark
import com.stylianosgakis.mars.theme.surfaceDimDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceDimDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceDimLight
import com.stylianosgakis.mars.theme.surfaceDimLightHighContrast
import com.stylianosgakis.mars.theme.surfaceDimLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceLight
import com.stylianosgakis.mars.theme.surfaceLightHighContrast
import com.stylianosgakis.mars.theme.surfaceLightMediumContrast
import com.stylianosgakis.mars.theme.surfaceVariantDark
import com.stylianosgakis.mars.theme.surfaceVariantDarkHighContrast
import com.stylianosgakis.mars.theme.surfaceVariantDarkMediumContrast
import com.stylianosgakis.mars.theme.surfaceVariantLight
import com.stylianosgakis.mars.theme.surfaceVariantLightHighContrast
import com.stylianosgakis.mars.theme.surfaceVariantLightMediumContrast
import com.stylianosgakis.mars.theme.tertiaryContainerDark
import com.stylianosgakis.mars.theme.tertiaryContainerDarkHighContrast
import com.stylianosgakis.mars.theme.tertiaryContainerDarkMediumContrast
import com.stylianosgakis.mars.theme.tertiaryContainerLight
import com.stylianosgakis.mars.theme.tertiaryContainerLightHighContrast
import com.stylianosgakis.mars.theme.tertiaryContainerLightMediumContrast
import com.stylianosgakis.mars.theme.tertiaryDark
import com.stylianosgakis.mars.theme.tertiaryDarkHighContrast
import com.stylianosgakis.mars.theme.tertiaryDarkMediumContrast
import com.stylianosgakis.mars.theme.tertiaryLight
import com.stylianosgakis.mars.theme.tertiaryLightHighContrast
import com.stylianosgakis.mars.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,

    )

// https://material-foundation.github.io/material-theme-builder/
@Composable
fun MarsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

