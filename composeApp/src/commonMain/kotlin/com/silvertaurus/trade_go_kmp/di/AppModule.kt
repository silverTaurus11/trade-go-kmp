package com.silvertaurus.trade_go_kmp.di

import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    networkModule,
    platformModule(),
    dataBaseModule,
    repositoryModule,
    useCaseModule
)


