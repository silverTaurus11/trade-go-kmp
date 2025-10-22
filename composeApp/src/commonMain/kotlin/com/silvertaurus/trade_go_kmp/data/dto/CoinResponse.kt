package com.silvertaurus.trade_go_kmp.data.dto

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import kotlinx.serialization.Serializable

@Serializable
data class CoinListResponse(
    val data: List<CoinItemDto>
)

@Serializable
data class CoinItemDto(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String? = null,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String
)

fun CoinItemDto.toDomain() = Coin(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank.toInt(),
    priceUsd = priceUsd.toDouble(),
    changePercent24Hr = changePercent24Hr.toDouble(),
    marketCapUsd = marketCapUsd.toDouble()
)
