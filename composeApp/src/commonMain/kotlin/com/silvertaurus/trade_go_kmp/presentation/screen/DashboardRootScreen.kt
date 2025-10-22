package com.silvertaurus.trade_go_kmp.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.presentation.component.BottomNavigationBar
import com.silvertaurus.trade_go_kmp.presentation.component.DashboardTab

@Composable
fun DashboardRootScreen(
    coins: List<Coin>,
    watchlist: Set<String>,
    watchListCoins: List<Coin>,
    onSelectCoin: (String) -> Unit,
    onToggleWatchlist: (String) -> Unit
) {
    var selectedTab by remember { mutableStateOf(DashboardTab.All) }

    Scaffold(
        containerColor = Color(0xFF0B0B0B), // hitam
        contentColor = Color(0xFF00FF87),   // hijau terang
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { padding ->
        when (selectedTab) {
            DashboardTab.All -> AllListScreen(
                coins = coins,
                watchlist = watchlist,
                onSelectCoin = onSelectCoin,
                onToggleWatchlist = onToggleWatchlist,
                modifier = Modifier.padding(padding)
            )
            DashboardTab.Watchlist -> WatchListScreen(
                coins = watchListCoins,
                watchlist = watchlist,
                onSelectCoin = onSelectCoin,
                onToggleWatchlist = onToggleWatchlist,
                modifier = Modifier.padding(padding)
            )
        }
    }
}