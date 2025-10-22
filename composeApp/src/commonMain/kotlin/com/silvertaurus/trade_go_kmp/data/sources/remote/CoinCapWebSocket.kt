package com.silvertaurus.trade_go_kmp.data.sources.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


class CoinCapWebsocket(private val client: HttpClient) {

    fun priceUpdatesFlow(assets: List<String>) = callbackFlow<Map<String, Double>> {
        val assetParam = assets.joinToString(",")
        val wsUrl = "${ApiConfig.WS_BASE_URL}/prices?assets=$assetParam"

        val session = client.webSocketSession {
            url(wsUrl)
            header(HttpHeaders.Authorization, "Bearer ${ApiConfig.TOKEN}")
        }

        launch {
            for (frame in session.incoming) {
                if (frame is Frame.Text) {
                    val json = frame.readText()
                    val prices = Json.decodeFromString<Map<String, String>>(json)
                        .mapValues { it.value.toDoubleOrNull() ?: 0.0 }
                    trySend(prices)
                }
            }
        }

        awaitClose {
            launch {
                try {
                    session.close(CloseReason(CloseReason.Codes.NORMAL, "Client closed"))
                } catch (e: Exception) {
                    println("WebSocket close error: ${e.message}")
                }
            }
        }
    }
}
