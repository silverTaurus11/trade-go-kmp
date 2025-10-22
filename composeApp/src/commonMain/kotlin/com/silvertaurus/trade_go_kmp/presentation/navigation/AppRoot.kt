package com.silvertaurus.trade_go_kmp.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnStart
import com.arkivanov.essenty.lifecycle.doOnStop
import com.silvertaurus.trade_go_kmp.data.sources.remote.socket.CoinCapWebsocket
import com.silvertaurus.trade_go_kmp.presentation.screen.DashboardScreen
import com.silvertaurus.trade_go_kmp.presentation.screen.DetailScreen
import com.silvertaurus.trade_go_kmp.presentation.screen.SplashScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppRoot(componentContext: ComponentContext) : ComponentContext by componentContext,
    KoinComponent {
    private val navigation = StackNavigation<Config>()
    private val socketManager: CoinCapWebsocket by inject()

    init {
        lifecycle.doOnStart {
            println("🌐 App started → WebSocket ON")
            socketManager.start()
        }
        lifecycle.doOnStop {
            println("🛑 App stopped → WebSocket OFF")
            socketManager.stop()
        }
    }

    val childStack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        initialStack = { listOf(Config.Splash) },
        handleBackButton = true,
        saveStack = { null },
        restoreStack = { null },
        key = "AppRootNavigation",
        childFactory = ::createChild
    )

    @OptIn(DelicateDecomposeApi::class)
    private fun createChild(config: Config, ctx: ComponentContext): Child =
        when (config) {
            is Config.Splash -> Child.Splash(SplashScreen(ctx, navigateToDashboard = {
                navigation.replaceCurrent(Config.Dashboard)
            }))

            is Config.Dashboard -> Child.Dashboard(DashboardScreen(onSelectCoin = {
                navigation.push(Config.Detail(it))
            }))

            is Config.Detail -> Child.Detail(DetailScreen(coinId = config.id, onBack = {
                navigation.pop()
            }))
        }

    sealed class Config {
        data object Splash : Config()
        data object Dashboard : Config()
        data class Detail(val id: String) : Config()
    }

    sealed class Child {
        data class Splash(val component: SplashScreen) : Child()
        data class Dashboard(val component: DashboardScreen) : Child()
        data class Detail(val component: DetailScreen) : Child()
    }
}