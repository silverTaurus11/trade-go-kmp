package com.silvertaurus.trade_go_kmp.shared

import java.text.DecimalFormat

actual fun formatPrice(value: Double): String {
    val formatter = DecimalFormat("#,###.00")
    return "$" + formatter.format(value)
}