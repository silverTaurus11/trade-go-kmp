
# 🪙 TradeGo Crypto Tracker (Kotlin Multiplatform)

**TradeGo** is a **Crypto Tracker app** built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**, targeting **Android and iOS** with a shared codebase.

The app uses **CoinCap API (REST + WebSocket)** for live crypto data and is architected using **Clean Architecture**, **Koin Dependency Injection**, and **SQLDelight** for local persistence.

---

## 🚀 Features

### 💹 1. Real-time Crypto Market Data

* Displays the top 100 crypto assets (Bitcoin, Ethereum, etc.) from [CoinCap.io](https://coincap.io).
* Supports **live price updates** through WebSocket streaming.

### ⭐ 2. Watchlist

* Add or remove assets from your personal watchlist.
* Data is persisted locally using **SQLDelight**.

### 📈 3. Coin Detail & Chart

* Shows detailed asset information.
* Includes an interactive **line chart** with selectable intervals:

  * 1 Minute
  * 15 Minutes
  * 1 Hour
* Built using **AAY Chart** (Compose Multiplatform).

### 💾 4. Local Persistence

* Watchlist and recent data are cached with **SQLDelight**.
* Data syncs seamlessly between local and remote sources.

### 🧱 5. Clean Architecture

* Layered structure:

  * **Domain:** UseCases & Models
  * **Data:** Repositories + DataSources (Remote & Local)
  * **Presentation:** ViewModels (MVVM)
  * **UI:** Compose Multiplatform

### ⚙️ 6. Dependency Injection

* Powered by **Koin (v4.1.1)** for dependency management.
* Fully multiplatform, shared between Android & iOS targets.

### 🌐 7. Networking

* Built with **Ktor Client (CIO/Darwin)** for HTTP requests.
* **Ktor WebSocket** for real-time updates.
* **kotlinx.serialization** for JSON parsing.

### 🎨 8. Theme System

* Dark, crypto-inspired theme: black background, green highlights, and white text.
* Consistent across all screens — Splash, Dashboard, and Detail.

### 🧩 9. Adaptive Launcher Icon Generator

* Automated Gradle task: `generateAppIcons`

  * Generates Android icons (`ic_launcher`, `ic_launcher_round`, adaptive XMLs)
  * Generates iOS icons (`AppIcon.appiconset + Contents.json`)
  * Requires only **one source file:**
    `composeApp/src/commonMain/composeResources/files/icon.png`

---

## 🧠 Architecture & Tech Stack

| Layer                    | Technology                           |
| ------------------------ | ------------------------------------ |
| **UI**                   | Compose Multiplatform                |
| **Navigation**           | Decompose + Essenty Lifecycle        |
| **State Management**     | MVVM + Kotlin Flows                  |
| **Dependency Injection** | Koin 4.1.1                           |
| **Networking**           | Ktor Client + WebSocket              |
| **Serialization**        | kotlinx.serialization                |
| **Database**             | SQLDelight (Android + Native Driver) |
| **Chart Library**        | AAY Chart (1.1.0)                    |
| **Theme**                | Custom Dark Green Material 3         |
| **Icon Generator**       | Gradle + ImageMagick                 |

---

## 📦 Dependency Versions

```toml
[versions]
agp = "8.11.2"
android-compileSdk = "36"
android-minSdk = "24"
android-targetSdk = "36"
androidDriver = "1.5.5"
androidx-activity = "1.11.0"
androidx-appcompat = "1.7.1"
androidx-core = "1.17.0"
androidx-espresso = "3.7.0"
androidx-lifecycle = "2.9.4"
androidx-testExt = "1.3.0"
composeCharts = "1.1.0"
composeMultiplatform = "1.9.0"
coroutinesExtensions = "1.5.5"
decompose = "3.4.0"
extensionsCompose = "3.4.0"
junit = "4.13.2"
koinCore = "4.1.1"
kotlin = "2.2.20"
kotlinxCoroutinesCore = "1.10.2"
kotlinxSerializationCore = "1.9.0"
ktorClientCio = "3.3.1"
ktorClientLogging = "3.3.1"
lifecycle = "2.5.0"
nativeDriver = "1.5.5"
runtime = "1.5.5"
```

---

## 🔧 Library Highlights

| Library                            | Purpose                  |
| ---------------------------------- | ------------------------ |
| **Koin**                           | Dependency Injection     |
| **Ktor (CIO, WebSocket, Logging)** | Network + Streaming      |
| **SQLDelight**                     | Local Database           |
| **Decompose + Essenty**            | Navigation & Lifecycle   |
| **Compose-Charts (AAY)**           | Line Chart Visualization |
| **Kotlinx Serialization**          | JSON Parsing             |
| **Kotlinx Coroutines**             | Async operations         |
| **Material 3 Compose**             | Modern UI components     |

---

## 📁 Project Structure

```
TradeGoKMP/
 ├── composeApp/
 │   ├── src/commonMain/composeResources/files/icon.png
 │   ├── src/androidMain/
 │   ├── src/iosMain/
 │   ├── src/commonMain/kotlin/
 │   └── build.gradle.kts
 ├── iosApp/
 │   ├── Assets.xcassets/
 │   └── Info.plist
 ├── build.gradle.kts
 └── settings.gradle.kts
```

---

## 🪄 Automated Tasks

### 🎨 Generate Launcher Icons

```bash
./gradlew generateAppIcons
```

Outputs:

* Android: `ic_launcher`, `ic_launcher_round`, adaptive XMLs
* iOS: `AppIcon.appiconset`

---

## 🧩 Build Android App

### macOS / Linux

```bash
./gradlew :composeApp:assembleDebug
```

### Windows

```bash
.\gradlew.bat :composeApp:assembleDebug
```

---

## 🍏 Build & Run iOS App

You can:

* Use the **Run configuration** from your IDE
  **or**
* Open `/iosApp` in **Xcode** and click **Run ▶️**

---

## 📱 Screens Overview

| Screen        | Description                                                     |
| ------------- | --------------------------------------------------------------- |
| **Splash**    | Animated intro → auto navigates to dashboard                    |
| **Dashboard** | List of 100 crypto assets + real-time price updates + Watchlist |
| **Detail**    | Detailed info + interactive chart (1m / 15m / 1h intervals)     |

---

## 🎨 App Theme

| Element    | Color      |
| ---------- | ---------- |
| Background | `#0D0F10`  |
| Surface    | `#141618`  |
| Primary    | `#00FF87`  |
| Secondary  | `#00C16A`  |
| Text       | `#FFFFFF`  |
| Accent     | Neon Green |
| Typography | Material 3 |

---

## 🧠 Multiplatform Structure Explanation

This is a **Kotlin Multiplatform project** targeting **Android** and **iOS**.

* `/composeApp`
  Contains all **shared Compose Multiplatform code**.

  * `commonMain` → Shared logic and UI for all targets.
  * `androidMain` / `iosMain` → Platform-specific implementations (e.g., database drivers, networking engines).

* `/iosApp`
  The iOS entry point.
  Even when using Compose Multiplatform for UI, you still need an iOS app target.
  This is also where you can integrate **SwiftUI** or **Apple-specific APIs** if needed.

---

## 🧑‍💻 Build Instructions

### Build and Run Android Application

#### macOS/Linux

```bash
./gradlew :composeApp:assembleDebug
```

#### Windows

```bash
.\gradlew.bat :composeApp:assembleDebug
```

### Build and Run iOS Application

* Use the **Run configuration** in your IDE
  **or**
* Open `/iosApp` in **Xcode** and run it directly.

---

## 🏁 Credits

Developed with ❤️ using
**Kotlin Multiplatform, Compose Multiplatform, Koin, Decompose, SQLDelight, and Ktor**
