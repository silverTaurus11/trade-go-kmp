package com.silvertaurus.trade_go_kmp.data.sources.interfaces

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun observeCachedAssets(): Flow<List<Coin>>
    suspend fun cacheAssets(coins: List<Coin>)

    fun observeWatchlist(): Flow<Set<String>>
    suspend fun toggleWatchlist(id: String)
}