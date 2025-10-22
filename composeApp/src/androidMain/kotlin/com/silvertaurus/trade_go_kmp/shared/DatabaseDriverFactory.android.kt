package com.silvertaurus.trade_go_kmp.shared

import android.content.Context
import com.silvertaurus.trade_go_kmp.composeApp.local.db.CryptoDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CryptoDatabase.Schema, context, "crypto.db")
    }
}