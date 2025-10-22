package com.silvertaurus.trade_go_kmp.domain.usecase

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class GetAssetsUseCase(
    private val repository: CoinRepository
) {
    fun observe(): Flow<List<Coin>> = repository.observeAssets()

    suspend fun refresh(): List<Coin> = repository.fetchAssets()
}