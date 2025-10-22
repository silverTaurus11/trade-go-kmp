package com.silvertaurus.trade_go_kmp.data.sources.remote.restapi

import com.silvertaurus.trade_go_kmp.data.dto.CoinDetailWrapper
import com.silvertaurus.trade_go_kmp.data.dto.CoinListResponse
import com.silvertaurus.trade_go_kmp.data.dto.PriceHistoryWrapper
import com.silvertaurus.trade_go_kmp.data.dto.toDomain
import com.silvertaurus.trade_go_kmp.data.sources.remote.restapi.ApiConfig
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CoinCapApi(private val client: HttpClient) {

    private val baseUrl = ApiConfig.BASE_URL

    suspend fun getAssets(limit: Int = 100): List<Coin> {
        val response: CoinListResponse = client.get("$baseUrl/assets") {
            parameter("limit", limit)
        }.body()
        return response.data.map { it.toDomain() }
    }

    suspend fun getAssetDetail(id: String): CoinDetail {
        val response: CoinDetailWrapper = client.get("$baseUrl/assets/$id").body()
        return response.data.toDomain()
    }

    suspend fun getPriceHistory(id: String, interval: String = "d1"): List<PriceHistoryPoint> {
        val response: PriceHistoryWrapper = client.get("$baseUrl/assets/$id/history") {
            parameter("interval", interval)
        }.body()
        return response.data?.map { it.toDomain() }.orEmpty()
    }
}