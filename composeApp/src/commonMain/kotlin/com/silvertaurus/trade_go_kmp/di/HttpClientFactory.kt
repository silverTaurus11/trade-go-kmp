package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.data.sources.remote.restapi.ApiConfig
import com.silvertaurus.trade_go_kmp.shared.provideEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(): HttpClient = HttpClient(provideEngine()) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
        install(DefaultRequest.Plugin) {
            header(HttpHeaders.Authorization, "Bearer ${ApiConfig.TOKEN}")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(WebSockets.Plugin)
    }
}