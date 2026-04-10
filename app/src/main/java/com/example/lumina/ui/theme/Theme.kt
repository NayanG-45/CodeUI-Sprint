package com.example.lumina.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.core.view.WindowCompat

val VintageAmber = Color(0xFFE5A93C)
val CharcoalDark = Color(0xFF0C0C0E)
val CharcoalSurface = Color(0xFF1A1A1E)

private val DarkColorScheme = darkColorScheme(
    primary = VintageAmber,
    secondary = Color.Gray,
    tertiary = Color.LightGray,
    background = CharcoalDark,
    surface = CharcoalSurface,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = VintageAmber,
    secondary = Color.DarkGray,
    tertiary = Color.Gray,
    background = Color(0xFFF8F9FA),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF121212),
    onSurface = Color(0xFF121212),
)

val AppTypography = Typography(
    titleLarge = Typography().titleLarge.copy(fontFamily = FontFamily.Serif),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = FontFamily.Serif),
    labelSmall = Typography().labelSmall.copy(fontFamily = FontFamily.Monospace),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = FontFamily.Monospace),
    bodyLarge = Typography().bodyLarge 
)

@Composable
fun LuminaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}