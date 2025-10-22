package com.silvertaurus.trade_go_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform