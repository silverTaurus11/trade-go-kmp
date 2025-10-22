package com.silvertaurus.trade_go_kmp.domain.model

data class CoinDetail(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val changePercent24Hr: Double,
    val marketCapUsd: Double,
    val volumeUsd24Hr: Double,
    val supply: Double,
    val explorerUrl: String?
)
