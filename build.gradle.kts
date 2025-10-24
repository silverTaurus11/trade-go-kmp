plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    id("com.squareup.sqldelight") version "1.5.5" apply false
    kotlin("plugin.serialization") version "2.0.0" apply false
    id("com.github.triplet.play") version "3.12.1" apply false
}