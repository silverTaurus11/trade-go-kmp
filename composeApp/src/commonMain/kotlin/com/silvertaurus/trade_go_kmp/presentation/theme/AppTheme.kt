package com.silvertaurus.trade_go_kmp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🎨 Palet warna utama (crypto dark theme)
val GreenPrimary = Color(0xFF00FF87)
val GreenAccent = Color(0xFF00C16A)
val GreenDim = Color(0xFF009E56)
val BlackBackground = Color(0xFF0D0F10)
val DarkSurface = Color(0xFF141618)
val TextWhite = Color(0xFFFFFFFF)
val TextGray = Color(0xFFB0B0B0)
val ErrorRed = Color(0xFFFF5252)

// 🌙 Dark theme (default)
private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    secondary = GreenAccent,
    background = BlackBackground,
    surface = DarkSurface,
    error = ErrorRed,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = TextWhite,
    onSurface = TextWhite,
    onError = TextWhite
)

// ☀️ Light theme (optional, jarang dipakai di crypto app)
private val LightColorScheme = lightColorScheme(
    primary = GreenDim,
    secondary = GreenAccent,
    background = Color.White,
    surface = Color(0xFFF3F3F3),
    onPrimary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// ✍️ Typography
val TradeGoTypography = Typography(
    titleLarge = Typography().titleLarge.copy(color = TextWhite),
    bodyLarge = Typography().bodyLarge.copy(color = TextWhite),
    bodyMedium = Typography().bodyMedium.copy(color = TextGray),
    labelLarge = Typography().labelLarge.copy(color = GreenPrimary)
)

// 💚 THEME COMPOSABLE
@Composable
fun TradeGoTheme(
    darkTheme: Boolean = true, // always dark for crypto look
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme || isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TradeGoTypography,
        content = content
    )
}