package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    selectedTab: DashboardTab,
    onTabSelected: (DashboardTab) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF101010), // hitam keabu
        contentColor = Color(0xFF00FF87)
    ) {
        NavigationBarItem(
            selected = selectedTab == DashboardTab.All,
            onClick = { onTabSelected(DashboardTab.All) },
            icon = { Icon(Icons.Default.List, contentDescription = "All") },
            label = {
                Text(
                    "All",
                    style = MaterialTheme.typography.labelSmall,
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF00FF87),
                selectedTextColor = Color(0xFF00FF87),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFF1A1A1A)
            )
        )
        NavigationBarItem(
            selected = selectedTab == DashboardTab.Watchlist,
            onClick = { onTabSelected(DashboardTab.Watchlist) },
            icon = { Icon(Icons.Default.Star, contentDescription = "Watchlist") },
            label = {
                Text(
                    "Watchlist",
                    style = MaterialTheme.typography.labelSmall,
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF00FF87),
                selectedTextColor = Color(0xFF00FF87),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFF1A1A1A)
            )
        )
    }
}

enum class DashboardTab {
    All, Watchlist
}