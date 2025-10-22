package com.silvertaurus.trade_go_kmp.data.dto

import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailWrapper(
    val data: CoinDetailDto
)

@Serializable
data class CoinDetailDto(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val explorer: String? = null
)

fun CoinDetailDto.toDomain() = CoinDetail(
    id = id,
    name = name,
    symbol = symbol,
    priceUsd = priceUsd.toDouble(),
    changePercent24Hr = changePercent24Hr.toDouble(),
    marketCapUsd = marketCapUsd.toDouble(),
    volumeUsd24Hr = volumeUsd24Hr.toDouble(),
    supply = supply.toDouble(),
    explorerUrl = explorer
)
