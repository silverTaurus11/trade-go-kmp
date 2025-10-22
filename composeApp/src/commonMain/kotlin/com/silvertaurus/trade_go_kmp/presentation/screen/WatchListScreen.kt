package com.silvertaurus.trade_go_kmp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.presentation.component.CoinRow

@Composable
fun WatchListScreen(
    coins: List<Coin>,
    watchlist: Set<String>,
    onSelectCoin: (String) -> Unit,
    onToggleWatchlist: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (coins.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("No favorite coins yet", color = Color.Gray)
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(coins) { coin ->
                CoinRow(
                    coin = coin,
                    isFavorite = true,
                    onSelect = onSelectCoin,
                    onToggle = onToggleWatchlist
                )
            }
        }
    }
}