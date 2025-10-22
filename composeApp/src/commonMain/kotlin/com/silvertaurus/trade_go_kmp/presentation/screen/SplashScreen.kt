package com.silvertaurus.trade_go_kmp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.delay

class SplashScreen(
    private val context: ComponentContext,
    private val navigateToDashboard: () -> Unit
) {
    @Composable
    fun Render() {
        SplashContent(onFinish = navigateToDashboard)
    }
}

@Composable
fun SplashContent(onFinish: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onFinish()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("🚀 Crypto Tracker", style = MaterialTheme.typography.headlineMedium)
    }
}