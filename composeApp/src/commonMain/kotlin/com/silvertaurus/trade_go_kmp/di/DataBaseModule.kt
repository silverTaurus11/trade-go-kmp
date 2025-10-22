package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.composeApp.local.db.CryptoDatabase
import com.silvertaurus.trade_go_kmp.data.sources.interfaces.LocalDataSource
import com.silvertaurus.trade_go_kmp.data.sources.local.LocalDataSourceImpl
import org.koin.dsl.module

val dataBaseModule = module {
    // Local
    single { CryptoDatabase(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}