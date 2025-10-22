package com.silvertaurus.trade_go_kmp.data.sources.interfaces

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun fetchAssets(limit: Int = 100): List<Coin>
    suspend fun getAssetDetail(id: String): CoinDetail
    suspend fun getPriceHistory(id: String, interval: String = "d1"): List<PriceHistoryPoint>
    fun observePriceUpdates(ids: List<String>): Flow<Map<String, Double>>
}
