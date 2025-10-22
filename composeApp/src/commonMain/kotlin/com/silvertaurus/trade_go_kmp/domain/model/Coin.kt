package com.silvertaurus.trade_go_kmp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val priceUsd: Double,
    val changePercent24Hr: Double,
    val marketCapUsd: Double,
)