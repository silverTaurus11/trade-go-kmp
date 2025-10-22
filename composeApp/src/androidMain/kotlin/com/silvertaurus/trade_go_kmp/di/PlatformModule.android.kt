package com.silvertaurus.trade_go_kmp.di

import com.silvertaurus.trade_go_kmp.shared.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(androidContext()).createDriver() }
}