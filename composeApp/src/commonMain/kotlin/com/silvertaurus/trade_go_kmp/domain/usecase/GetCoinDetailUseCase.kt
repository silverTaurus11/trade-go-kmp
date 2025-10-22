package com.silvertaurus.trade_go_kmp.domain.usecase

import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository

class GetCoinDetailUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(id: String): CoinDetail {
        return repository.getAssetDetail(id)
    }
}