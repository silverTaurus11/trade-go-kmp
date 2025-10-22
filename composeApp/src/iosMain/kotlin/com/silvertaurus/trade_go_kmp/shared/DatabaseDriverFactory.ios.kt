package com.silvertaurus.trade_go_kmp.shared

import com.silvertaurus.trade_go_kmp.composeApp.local.db.CryptoDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CryptoDatabase.Schema, "crypto.db")
    }
}