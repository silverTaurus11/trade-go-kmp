package com.silvertaurus.trade_go_kmp.data.sources.remote

import com.silvertaurus.trade_go_kmp.data.sources.interfaces.RemoteDataSource
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val api: CoinCapApi,
    private val ws: CoinCapWebsocket
) : RemoteDataSource {

    override suspend fun fetchAssets(limit: Int): List<Coin> {
        return api.getAssets(limit)
    }

    override suspend fun getAssetDetail(id: String): CoinDetail {
        return api.getAssetDetail(id)
    }

    override suspend fun getPriceHistory(id: String, interval: String): List<PriceHistoryPoint> {
        return api.getPriceHistory(id, interval)
    }

    override fun observePriceUpdates(ids: List<String>): Flow<Map<String, Double>> {
        return ws.priceUpdatesFlow(ids)
    }
}