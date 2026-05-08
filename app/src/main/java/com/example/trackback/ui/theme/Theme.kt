package com.example.trackback.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ForexColorScheme = darkColorScheme(
    primary = GhostPurple,
    secondary = NeonBlue,
    tertiary = SuccessGreen,
    background = DarkGrey,
    surface = SurfaceGrey,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = ErrorRed
)

private val BusinessColorScheme = darkColorScheme(
    primary = FirmanagerPrimary,
    secondary = FirmanagerTeal,
    tertiary = FirmanagerGreen,
    background = FirmanagerBackground,
    surface = FirmanagerSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = ErrorRed
)

private val FitnessColorScheme = darkColorScheme(
    primary = SunsetOrange,
    secondary = EnergyYellow,
    tertiary = SuccessGreen,
    background = Color(0xFF0A0505),
    surface = Color(0xFF140F0F),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = ErrorRed
)

@Composable
fun TrackbackTheme(
    domain: String = "forex",
    content: @Composable () -> Unit
) {
    val colorScheme = when(domain) {
        "business" -> BusinessColorScheme
        "fitness" -> FitnessColorScheme
        else -> ForexColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.background.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
