package com.silvertaurus.trade_go_kmp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.arkivanov.decompose.ComponentContext
import com.silvertaurus.trade_go_kmp.domain.usecase.GetAssetsUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObserveWatchlistUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ToggleWatchlistUseCase
import com.silvertaurus.trade_go_kmp.presentation.viewmodel.DashboardViewModel
import org.koin.compose.koinInject

class DashboardScreen(
    private val context: ComponentContext,
    private val onSelectCoin: (String) -> Unit
) {
    @Composable
    fun Render() {
        val viewModel = rememberDashboardViewModel()
        val coins by viewModel.coins.collectAsState()
        val watchlist by viewModel.watchlist.collectAsState()

        DashboardRootScreen(
            coins = coins,
            watchlist = watchlist,
            onSelectCoin = onSelectCoin,
            onToggleWatchlist = viewModel::toggleWatchlistAction
        )
    }
}

@Composable
fun rememberDashboardViewModel(): DashboardViewModel {
    val getAssets: GetAssetsUseCase = koinInject()
    val toggleWatchlist: ToggleWatchlistUseCase = koinInject()
    val observeWatchlist: ObserveWatchlistUseCase = koinInject()
    return remember { DashboardViewModel(getAssets, toggleWatchlist, observeWatchlist) }
}
