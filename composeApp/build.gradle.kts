import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.File

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.websockets)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines.extensions)

            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.ktor.client.cio)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.koin.android)
            implementation(libs.compose.charts)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.silvertaurus.trade_go_kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.silvertaurus.trade_go_kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

sqldelight {
    database("CryptoDatabase") {
        packageName = "com.silvertaurus.trade_go_kmp.composeApp.local.db"
        sourceFolders = listOf("sqldelight")
    }
}

abstract class GenerateAppIconsTask : DefaultTask() {
    @get:InputFile
    abstract val iconSource: RegularFileProperty

    @get:OutputDirectory
    abstract val androidResDir: DirectoryProperty

    @get:OutputDirectory
    abstract val iosResDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val icon = iconSource.get().asFile
        val androidRes = androidResDir.get().asFile
        val iosAppIconSet = iosResDir.get().asFile

        if (!icon.exists()) {
            throw IllegalArgumentException("❌ Source icon not found: ${icon.absolutePath}")
        }

        val magickCmd = if (System.getProperty("os.name").startsWith("Windows")) "magick" else "convert"
        println("🎨 Generating launcher icons from ${icon.name}")

        // --- ANDROID ---
        val mipmapSizes = mapOf(
            "mipmap-mdpi" to 48,
            "mipmap-hdpi" to 72,
            "mipmap-xhdpi" to 96,
            "mipmap-xxhdpi" to 144,
            "mipmap-xxxhdpi" to 192
        )

        mipmapSizes.forEach { (folder, size) ->
            val dir = File(androidRes, folder)
            dir.mkdirs()

            val launcher = File(dir, "ic_launcher.png")
            val round = File(dir, "ic_launcher_round.png")
            val foreground = File(dir, "ic_launcher_foreground.png")

            project.exec {
                commandLine(magickCmd, icon.absolutePath, "-resize", "${size}x${size}", launcher.absolutePath)
            }
            project.exec {
                commandLine(magickCmd, icon.absolutePath, "-resize", "${size}x${size}", round.absolutePath)
            }
            project.exec {
                commandLine(magickCmd, icon.absolutePath, "-resize", "${size}x${size}", foreground.absolutePath)
            }

            println("✅ Android: $folder launcher icons (${size}px)")
        }

        // Generate adaptive XMLs
        val xmlDir = File(androidRes, "mipmap-anydpi-v26").apply { mkdirs() }

        fun makeAdaptiveXml(name: String) = """
            <adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
                <background android:drawable="@color/ic_launcher_background"/>
                <foreground android:drawable="@mipmap/ic_launcher_foreground"/>
            </adaptive-icon>
        """.trimIndent()

        File(xmlDir, "ic_launcher.xml").writeText(makeAdaptiveXml("ic_launcher"))
        File(xmlDir, "ic_launcher_round.xml").writeText(makeAdaptiveXml("ic_launcher_round"))

        // Generate background color resource if missing
        val colorXml = File(androidRes, "values/ic_launcher_background.xml")
        if (!colorXml.exists()) {
            colorXml.parentFile.mkdirs()
            colorXml.writeText(
                """
                <resources>
                    <color name="ic_launcher_background">#0D0F10</color>
                </resources>
                """.trimIndent()
            )
        }

        println("🧱 Android adaptive icons generated ✅")

        // --- iOS ---
        val iosSizes = listOf(20, 29, 40, 60, 76, 83, 1024)
        iosAppIconSet.mkdirs()

        iosSizes.forEach { size ->
            val out = File(iosAppIconSet, "AppIcon-${size}x${size}.png")
            project.exec {
                commandLine(magickCmd, icon.absolutePath, "-resize", "${size}x${size}", out.absolutePath)
            }
        }

        File(iosAppIconSet, "Contents.json").writeText(
            """
            {
              "images": [
                ${iosSizes.joinToString(",") { s ->
                """{"idiom":"iphone","size":"${s}x${s}","filename":"AppIcon-${s}x${s}.png","scale":"1x"}"""
            }}
              ],
              "info":{"version":1,"author":"TradeGo"}
            }
            """.trimIndent()
        )

        println("🍏 iOS icons generated in ${iosAppIconSet.absolutePath}")
    }
}

tasks.register<GenerateAppIconsTask>("generateAppIcons") {
    group = "icon"
    description = "Generate Android & iOS app launcher icons automatically"

    iconSource.set(layout.projectDirectory.file("../composeApp/src/commonMain/composeResources/drawable/icon.png"))
    androidResDir.set(layout.projectDirectory.dir("../composeApp/src/androidMain/res"))
    iosResDir.set(layout.projectDirectory.dir("../iosApp/Assets.xcassets/AppIcon.appiconset"))
}
