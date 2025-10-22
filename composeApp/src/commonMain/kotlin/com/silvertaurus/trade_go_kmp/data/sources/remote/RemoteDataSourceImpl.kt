package com.silvertaurus.trade_go_kmp.data.sources.remote

import com.silvertaurus.trade_go_kmp.data.sources.interfaces.RemoteDataSource
import com.silvertaurus.trade_go_kmp.data.sources.remote.restapi.CoinCapApi
import com.silvertaurus.trade_go_kmp.data.sources.remote.socket.CoinCapWebsocket
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import com.silvertaurus.trade_go_kmp.util.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningFold

class RemoteDataSourceImpl(
    private val api: CoinCapApi,
    private val ws: CoinCapWebsocket,
    private val dispatchersProvider: DispatchersProvider
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

    override fun observePriceUpdates(): Flow<Map<String, Double>> {
        return ws.prices.map { (symbol, price) -> mapOf(symbol to price) }
            .runningFold(emptyMap<String, Double>()) { acc, new ->
                acc + new
            }
            .distinctUntilChanged()
            .flowOn(dispatchersProvider.io)
    }
}