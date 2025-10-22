package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.data.sources.interfaces.RemoteDataSource
import com.silvertaurus.trade_go_kmp.di.HttpClientFactory
import com.silvertaurus.trade_go_kmp.data.sources.remote.RemoteDataSourceImpl
import com.silvertaurus.trade_go_kmp.data.sources.remote.restapi.ApiConfig
import com.silvertaurus.trade_go_kmp.data.sources.remote.restapi.CoinCapApi
import com.silvertaurus.trade_go_kmp.data.sources.remote.socket.CoinCapSocketClient
import com.silvertaurus.trade_go_kmp.data.sources.remote.socket.CoinCapWebsocket
import com.silvertaurus.trade_go_kmp.util.DispatchersProvider
import org.koin.dsl.module

val networkModule = module {
    single { DispatchersProvider() }
    single { HttpClientFactory.create() }
    single { CoinCapApi(get()) }
    single {
        CoinCapSocketClient(
            httpClient = get(),
            apiKey = ApiConfig.TOKEN
        )
    }
    single { CoinCapWebsocket(get(), get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get(), get()) }
}