package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.composeApp.local.db.CryptoDatabase
import com.silvertaurus.trade_go_kmp.data.sources.interfaces.LocalDataSource
import com.silvertaurus.trade_go_kmp.data.sources.interfaces.RemoteDataSource
import com.silvertaurus.trade_go_kmp.data.sources.local.LocalDataSourceImpl
import com.silvertaurus.trade_go_kmp.data.sources.remote.CoinCapApi
import com.silvertaurus.trade_go_kmp.data.sources.remote.CoinCapWebsocket
import com.silvertaurus.trade_go_kmp.data.sources.remote.HttpClientFactory
import com.silvertaurus.trade_go_kmp.data.sources.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val networkModule = module {
    // Remote
    single { HttpClientFactory.create() }
    single { CoinCapApi(get()) }
    single { CoinCapWebsocket(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get()) }
}