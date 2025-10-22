package com.silvertaurus.trade_go_kmp

import com.silvertaurus.trade_go_kmp.di.initKoin
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object KoinHelper {
    fun doInit() = initKoin()
}