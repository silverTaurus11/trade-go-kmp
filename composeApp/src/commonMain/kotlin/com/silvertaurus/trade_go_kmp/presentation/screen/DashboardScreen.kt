package com.silvertaurus.trade_go_kmp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silvertaurus.trade_go_kmp.presentation.viewmodel.ViewModelFactory.rememberDashboardViewModel

class DashboardScreen(
    private val onSelectCoin: (String) -> Unit
) {
    @Composable
    fun Render() {
        val viewModel = rememberDashboardViewModel()
        val coins by viewModel.coins.collectAsState()
        val watchlist by viewModel.watchlist.collectAsState()
        val watchlistCoins by viewModel.watchlistCoins.collectAsState()

        DashboardRootScreen(
            coins = coins,
            watchlist = watchlist,
            watchListCoins = watchlistCoins,
            onSelectCoin = onSelectCoin,
            onToggleWatchlist = viewModel::toggleWatchlistAction
        )
    }
}
