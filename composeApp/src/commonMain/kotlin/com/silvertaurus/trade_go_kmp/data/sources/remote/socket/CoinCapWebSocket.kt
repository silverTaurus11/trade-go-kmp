package com.silvertaurus.trade_go_kmp.data.sources.remote.socket

import com.silvertaurus.trade_go_kmp.util.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.io.EOFException
import kotlin.coroutines.cancellation.CancellationException


class CoinCapWebsocket(
    private val client: CoinCapSocketClient,
    dispatchersProvider: DispatchersProvider
) {

    private val scope = CoroutineScope(SupervisorJob() + dispatchersProvider.io)

    private val _prices = MutableSharedFlow<Pair<String, Double>>(replay = 1)
    val prices = _prices.asSharedFlow()

    private var socketJob: Job? = null

    fun start() {
        if (socketJob?.isActive == true) return

        socketJob = scope.launch {
            println("🚀 Starting WebSocket listener...")

            while (isActive) {
                try {
                    client.connectAll().collect { (symbol, price) ->
                        _prices.emit(symbol to price)
                    }
                } catch (e: EOFException) {
                    println("⚠️ WebSocket closed (EOF): ${e.message}")
                    delay(3_000) // ⏳ reconnect after 3s
                    println("🔁 Reconnecting WebSocket...")
                } catch (_: CancellationException) {
                    println("🛑 WebSocket canceled")
                    break
                } catch (e: Exception) {
                    println("🚨 WebSocket error: ${e.message}")
                    delay(5_000)
                    println("🔁 Retrying connection...")
                }
            }
        }
    }

    fun stop() {
        println("🛑 Stopping WebSocket")
        socketJob?.cancel()
        socketJob = null
    }
}
