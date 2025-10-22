package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.data.repository.CoinRepositoryImpl
import com.silvertaurus.trade_go_kmp.domain.repository.CoinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CoinRepository> { CoinRepositoryImpl(get(), get()) }
}