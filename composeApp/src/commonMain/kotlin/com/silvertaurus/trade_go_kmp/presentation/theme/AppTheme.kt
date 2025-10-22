package com.silvertaurus.trade_go_kmp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkGreenTheme = darkColorScheme(
    primary = Color(0xFF00FF87),
    secondary = Color(0xFF00C67A),
    background = Color(0xFF0B0B0B),
    surface = Color(0xFF101010),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color(0xFF00FF87),
    onSurface = Color(0xFF00FF87)
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkGreenTheme,
        content = content
    )
}