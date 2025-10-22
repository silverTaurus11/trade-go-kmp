package com.silvertaurus.trade_go_kmp

import android.app.Application
import com.silvertaurus.trade_go_kmp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}
