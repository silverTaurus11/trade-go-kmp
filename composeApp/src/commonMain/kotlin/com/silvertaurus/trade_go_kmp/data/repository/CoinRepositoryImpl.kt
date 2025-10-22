package com.silvertaurus.trade_go_kmp.data.repository

import com.silvertaurus.trade_go_kmp.data.sources.interfaces.LocalDataSource
import com.silvertaurus.trade_go_kmp.data.sources.interfaces.RemoteDataSource
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class CoinRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : CoinRepository {

    override fun observeAssets(): Flow<List<Coin>> {
        return local.observeCachedAssets()
    }

    override suspend fun fetchAssets(): List<Coin> {
        val coins = remote.fetchAssets()
        local.cacheAssets(coins)
        return coins
    }

    override suspend fun getAssetDetail(id: String): CoinDetail {
        return remote.getAssetDetail(id)
    }

    override suspend fun getPriceHistory(id: String, interval: String): List<PriceHistoryPoint> {
        return remote.getPriceHistory(id, interval)
    }

    override fun observePriceUpdates(ids: List<String>): Flow<Map<String, Double>> {
        return remote.observePriceUpdates(ids)
    }

    override fun observeWatchlist(): Flow<Set<String>> {
        return local.observeWatchlist()
    }

    override suspend fun toggleWatchlist(id: String) {
        local.toggleWatchlist(id)
    }
}
