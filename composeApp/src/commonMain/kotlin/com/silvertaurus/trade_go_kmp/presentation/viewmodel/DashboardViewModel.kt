package com.silvertaurus.trade_go_kmp.presentation.viewmodel

import com.silvertaurus.trade_go_kmp.domain.model.Coin
import com.silvertaurus.trade_go_kmp.domain.usecase.GetAssetsUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObservePriceUpdatesUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObserveWatchlistUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ToggleWatchlistUseCase
import com.silvertaurus.trade_go_kmp.util.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getAssets: GetAssetsUseCase,
    private val toggleWatchlist: ToggleWatchlistUseCase,
    observeWatchlist: ObserveWatchlistUseCase,
    private val observePriceUpdates: ObservePriceUpdatesUseCase,
    dispatchers: DispatchersProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatchers.io)

    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins = _coins.asStateFlow()

    val watchlist = observeWatchlist()
        .stateIn(scope, SharingStarted.Eagerly, emptySet())

    val watchlistCoins: StateFlow<List<Coin>> = combine(
        _coins,
        watchlist
    ) { coins, ids ->
        coins.filter { it.id in ids }
    }.stateIn(scope, SharingStarted.Eagerly, emptyList())

    init {
        scope.launch {
            getAssets.refresh()
            getAssets.observe().collect { list ->
                _coins.value = list
            }
        }

        scope.launch {
            observePriceUpdates()
                .collect { priceMap ->
                    val updated = _coins.value.map { coin ->
                        val newPrice = priceMap[coin.id]
                        if (newPrice != null && newPrice != coin.priceUsd) {
                            coin.copy(priceUsd = newPrice)
                        } else coin
                    }
                    _coins.value = updated
                }
        }
    }

    fun toggleWatchlistAction(id: String) {
        scope.launch { toggleWatchlist(id) }
    }
}