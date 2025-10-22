package com.silvertaurus.trade_go_kmp.domain.repository

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun observeAssets(): Flow<List<Coin>>
    suspend fun fetchAssets(): List<Coin>
    suspend fun getAssetDetail(id: String): CoinDetail
    suspend fun getPriceHistory(id: String, interval: String): List<PriceHistoryPoint>

    fun observePriceUpdates(ids: List<String>): Flow<Map<String, Double>>
    fun observeWatchlist(): Flow<Set<String>>
    suspend fun toggleWatchlist(id: String)
}
