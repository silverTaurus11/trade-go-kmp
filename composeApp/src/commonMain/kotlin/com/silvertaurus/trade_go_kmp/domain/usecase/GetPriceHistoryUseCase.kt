package com.silvertaurus.trade_go_kmp.domain.usecase

import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository

class GetPriceHistoryUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(id: String, interval: String): List<PriceHistoryPoint> {
        return repository.getPriceHistory(id, interval)
    }
}