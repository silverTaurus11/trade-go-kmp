package com.silvertaurus.trade_go_kmp.data.sources.local


import com.silvertaurus.trade_go_kmp.composeApp.local.db.CryptoDatabase
import com.silvertaurus.trade_go_kmp.data.sources.interfaces.LocalDataSource
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val database: CryptoDatabase
) : LocalDataSource {

    private val coinQueries = database.cryptoDatabaseQueries

    override fun observeCachedAssets(): Flow<List<Coin>> {
        return coinQueries.selectAll().asFlow().map { query ->
            query.executeAsList().map {
                Coin(
                    id = it.id,
                    name = it.name,
                    symbol = it.symbol,
                    rank = it.rank.toInt(),
                    priceUsd = it.priceUsd,
                    changePercent24Hr = it.changePercent24Hr,
                    marketCapUsd = it.marketCapUsd
                )
            }
        }
    }

    override suspend fun cacheAssets(coins: List<Coin>) {
        coinQueries.transaction {
            coinQueries.clearAll()
            coins.forEach {
                coinQueries.insertItem(
                    id = it.id,
                    name = it.name,
                    symbol = it.symbol,
                    rank = it.rank.toLong(),
                    priceUsd = it.priceUsd,
                    changePercent24Hr = it.changePercent24Hr,
                    marketCapUsd = it.marketCapUsd
                )
            }
        }
    }

    override fun observeWatchlist(): Flow<Set<String>> {
        return database.cryptoDatabaseQueries.watchlistSelectAll().asFlow().map { query ->
            query.executeAsList().map { it }.toSet()
        }
    }

    override suspend fun toggleWatchlist(id: String) {
        val exists = database.cryptoDatabaseQueries.existsInWatchlist(id).executeAsOne()
        if (exists == 0L) {
            database.cryptoDatabaseQueries.insertWatchlistItem(id)
        } else {
            database.cryptoDatabaseQueries.deleteWatchlistItem(id)
        }
    }
}
