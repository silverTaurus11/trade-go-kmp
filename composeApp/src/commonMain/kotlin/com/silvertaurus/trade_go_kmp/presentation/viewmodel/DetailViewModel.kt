package com.silvertaurus.trade_go_kmp.presentation.viewmodel

import com.silvertaurus.trade_go_kmp.domain.model.CoinDetail
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import com.silvertaurus.trade_go_kmp.domain.usecase.GetCoinDetailUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.GetPriceHistoryUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.ObservePriceUpdatesUseCase
import com.silvertaurus.trade_go_kmp.util.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class Interval(val label: String) {
    MIN_1("1m"),
    MIN_15("15m"),
    HOUR_1("1h")
}

class DetailViewModel(
    private val id: String,
    private val getDetail: GetCoinDetailUseCase,
    private val getHistory: GetPriceHistoryUseCase,
    private val observePriceUpdates: ObservePriceUpdatesUseCase,
    dispatcher: DispatchersProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher.io)

    private val _coin = MutableStateFlow<CoinDetail?>(null)
    val coin = _coin.asStateFlow()

    private val _history = MutableStateFlow<List<PriceHistoryPoint>>(emptyList())
    val history = _history.asStateFlow()

    private val _interval = MutableStateFlow(Interval.MIN_1)
    val interval = _interval.asStateFlow()

    init {
        scope.launch {
            _coin.value = getDetail(id)
            loadHistory()
        }

        scope.launch {
            observePriceUpdates()
                .collect { priceMap ->
                    val newPrice = priceMap[id]
                    if (newPrice != null) {
                        val current = _coin.value
                        if (current != null && current.priceUsd != newPrice) {
                            _coin.value = current.copy(priceUsd = newPrice)
                            println("💹 [Realtime] ${current.symbol} updated price = $newPrice")
                        }
                    }
                }
        }
    }

    fun onIntervalChanged(newInterval: Interval) {
        _interval.value = newInterval
        scope.launch { loadHistory() }
    }

    private suspend fun loadHistory() {
        val intervalParam = when (_interval.value) {
            Interval.MIN_1 -> "m1"
            Interval.MIN_15 -> "m15"
            Interval.HOUR_1 -> "h1"
        }

        try {
            val result = getHistory(id, intervalParam)
            _history.value = result
            println("📊 Loaded ${result.size} history points for $intervalParam")
        } catch (e: Exception) {
            println("⚠️ Failed to load history: ${e.message}")
        }
    }
}