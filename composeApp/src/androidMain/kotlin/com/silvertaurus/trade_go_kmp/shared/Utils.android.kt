package com.silvertaurus.trade_go_kmp.shared

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import java.text.DecimalFormat

actual fun formatPrice(value: Double): String {
    val formatter = DecimalFormat("#,###.00")
    return "$" + formatter.format(value)
}

actual fun provideEngine(): HttpClientEngineFactory<*> = CIO