package com.silvertaurus.trade_go_kmp.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.silvertaurus.trade_go_kmp.domain.usecase.GetAssetsUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.GetCoinDetailUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.GetPriceHistoryUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObservePriceUpdatesUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObserveWatchlistUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ToggleWatchlistUseCase
import com.silvertaurus.trade_go_kmp.util.DispatchersProvider
import org.koin.compose.koinInject

object ViewModelFactory {

    @Composable
    fun rememberDashboardViewModel(): DashboardViewModel {
        val getAssets: GetAssetsUseCase = koinInject()
        val toggleWatchlist: ToggleWatchlistUseCase = koinInject()
        val observeWatchlist: ObserveWatchlistUseCase = koinInject()
        val observePriceUpdatesUseCase: ObservePriceUpdatesUseCase = koinInject()
        val dispatchersProvider: DispatchersProvider = koinInject()

        return remember {
            DashboardViewModel(
                getAssets,
                toggleWatchlist,
                observeWatchlist,
                observePriceUpdatesUseCase,
                dispatchersProvider
            )
        }
    }

    @Composable
    fun rememberDetailViewModel(id: String): DetailViewModel {
        val getDetail: GetCoinDetailUseCase = koinInject()
        val getHistory: GetPriceHistoryUseCase = koinInject()
        val getPriceHistoryUseCase: ObservePriceUpdatesUseCase = koinInject()
        val dispatchersProvider: DispatchersProvider = koinInject()

        return remember {
            DetailViewModel(
                id,
                getDetail,
                getHistory,
                getPriceHistoryUseCase,
                dispatchersProvider
            )
        }
    }
}