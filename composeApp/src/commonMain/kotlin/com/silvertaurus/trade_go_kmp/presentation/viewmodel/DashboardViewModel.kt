package com.silvertaurus.trade_go_kmp.presentation.viewmodel

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.usecase.GetAssetsUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObserveWatchlistUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ToggleWatchlistUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getAssets: GetAssetsUseCase,
    private val toggleWatchlist: ToggleWatchlistUseCase,
    private val observeWatchlist: ObserveWatchlistUseCase
) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins = _coins.asStateFlow()

    val watchlist = observeWatchlist().stateIn(scope, SharingStarted.Eagerly, emptySet())

    init {
        scope.launch {
            getAssets.refresh()
            getAssets.observe().collect {
                _coins.value = it
            }
        }
    }

    fun toggleWatchlistAction(id: String) {
        scope.launch { toggleWatchlist(id) }
    }
}