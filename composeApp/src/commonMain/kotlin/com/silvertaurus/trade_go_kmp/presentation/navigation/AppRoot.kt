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
import com.silvertaurus.trade_go_kmp.presentation.screen.DashboardScreen
import com.silvertaurus.trade_go_kmp.presentation.screen.DetailScreen
import com.silvertaurus.trade_go_kmp.presentation.screen.SplashScreen

class AppRoot(componentContext: ComponentContext) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

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
            is Config.Dashboard -> Child.Dashboard(DashboardScreen(ctx, onSelectCoin = {
                navigation.push(Config.Detail(it))
            }))
            is Config.Detail -> Child.Detail(DetailScreen(ctx, coinId = config.id, onBack = {
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