package com.silvertaurus.trade_go_kmp.domain.usecase

import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository

class ToggleWatchlistUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(id: String) {
        repository.toggleWatchlist(id)
    }
}