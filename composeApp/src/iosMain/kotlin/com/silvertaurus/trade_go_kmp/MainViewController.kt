package com.silvertaurus.trade_go_kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.silvertaurus.trade_go_kmp.presentation.App
import platform.UIKit.UIViewController

fun MainViewController(lifecycle: LifecycleRegistry): UIViewController {
    val context = DefaultComponentContext(lifecycle = lifecycle)
    return ComposeUIViewController {
        App(context)
    }
}