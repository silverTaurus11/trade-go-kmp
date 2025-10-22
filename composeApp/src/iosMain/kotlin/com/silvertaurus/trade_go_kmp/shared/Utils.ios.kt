package com.silvertaurus.trade_go_kmp.shared

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual fun formatPrice(value: Double): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterDecimalStyle
        minimumFractionDigits = 2u
        maximumFractionDigits = 2u
    }
    return "$" + (formatter.stringFromNumber(NSNumber(value)) ?: value.toString())
}

actual fun provideEngine(): HttpClientEngineFactory<*> = Darwin