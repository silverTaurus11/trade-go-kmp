package com.silvertaurus.trade_go_kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.silvertaurus.trade_go_kmp.presentation.App

class MainActivity : ComponentActivity() {

    private val mainLifecycle = LifecycleRegistry()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        mainLifecycle.onCreate()
        setContent {
            App(DefaultComponentContext(lifecycle = mainLifecycle))
        }
    }

    override fun onStart() {
        super.onStart()
        mainLifecycle.onStart()
    }

    override fun onStop() {
        mainLifecycle.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mainLifecycle.onDestroy()
        super.onDestroy()
    }
}