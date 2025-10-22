package com.silvertaurus.trade_go_kmp.data.dto

import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import kotlinx.serialization.Serializable

@Serializable
data class PriceHistoryWrapper(
    val data: List<PricePointDto>? = null
)

@Serializable
data class PricePointDto(
    val priceUsd: String,
    val time: Long,
    val date: String
)

fun PricePointDto.toDomain() = PriceHistoryPoint(
    time = time,
    date = date,
    priceUsd = priceUsd.toDouble()
)