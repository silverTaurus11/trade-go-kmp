package com.silvertaurus.trade_go_kmp.domain.usecase

import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class ObservePriceUpdatesUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Map<String, Double>> {
        return repository.observePriceUpdates()
    }
}