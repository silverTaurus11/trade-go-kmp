package com.silvertaurus.trade_go_kmp.shared

import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.ktor.client.engine.HttpClientEngineFactory

expect fun formatPrice(value: Double): String

expect fun provideEngine(): HttpClientEngineFactory<*>

fun createLifecycle(): LifecycleRegistry = LifecycleRegistry()