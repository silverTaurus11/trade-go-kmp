package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.domain.usecase.GetAssetsUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.GetCoinDetailUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.GetPriceHistoryUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObservePriceUpdatesUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObserveWatchlistUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ToggleWatchlistUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAssetsUseCase(get()) }
    factory { GetCoinDetailUseCase(get()) }
    factory { GetPriceHistoryUseCase(get()) }
    factory { ObservePriceUpdatesUseCase(get()) }
    factory { ToggleWatchlistUseCase(get()) }
    factory { ObserveWatchlistUseCase(get()) }
}