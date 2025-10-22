package com.silvertaurus.trade_go_kmp.data.sources.remote.socket

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class CoinCapSocketClient(
    private val httpClient: HttpClient,
    private val apiKey: String
) {
    private val json = Json { ignoreUnknownKeys = true }

    fun connectAll(): Flow<Pair<String, Double>> = flow {
        val url = "wss://wss.coincap.io/prices?assets=ALL&apiKey=$apiKey"
        println("🌐 Connecting to WebSocket: $url")

        httpClient.webSocket(url) {
            for (frame in incoming) {
                if (!isActive) break
                val text = (frame as? Frame.Text)?.readText() ?: continue

                try {
                    val parsed = json.decodeFromString(JsonObject.serializer(), text)
                    parsed.forEach { (symbol, value) ->
                        emit(symbol to value.jsonPrimitive.content.toDouble())
                    }
                } catch (e: Exception) {
                    println("⚠️ WebSocket parse error: ${e.message}")
                }
            }
        }
    }
}
