package com.silvertaurus.trade_go_kmp.domain.model

data class PriceHistoryPoint(
    val time: Long,
    val date: String,
    val priceUsd: Double
)
