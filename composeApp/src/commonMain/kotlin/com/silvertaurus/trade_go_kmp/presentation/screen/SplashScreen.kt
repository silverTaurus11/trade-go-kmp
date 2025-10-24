package com.silvertaurus.trade_go_kmp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.silvertaurus.trade_go_kmp.presentation.theme.GreenPrimary
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
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                painter = rememberVectorPainter(Icons.Default.CurrencyBitcoin),
                contentDescription = "Bitcoin",
                tint = GreenPrimary
            )
            Text(
                "Trade Go",
                style = MaterialTheme.typography.headlineLarge.copy(color = GreenPrimary)
            )
        }

    }
}