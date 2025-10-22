package com.silvertaurus.trade_go_kmp.presentation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.silvertaurus.trade_go_kmp.presentation.navigation.AppRoot
import com.silvertaurus.trade_go_kmp.presentation.theme.AppTheme

@Composable
fun App(componentContext: DefaultComponentContext = DefaultComponentContext(LifecycleRegistry())) {
    AppTheme {
        val root = AppRoot(componentContext)
        Children(stack = root.childStack) {
            when (val child = it.instance) {
                is AppRoot.Child.Splash -> child.component.Render()
                is AppRoot.Child.Dashboard -> child.component.Render()
                is AppRoot.Child.Detail -> child.component.Render()
            }
        }
    }
}