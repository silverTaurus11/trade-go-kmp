package com.silvertaurus.trade_go_kmp.presentation.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.domain.usecase.GetCoinDetailUseCase
import com.silvertaurus.trade_go_kmp.domain.usecase.GetPriceHistoryUseCase
import com.silvertaurus.trade_go_kmp.presentation.component.ChartLineView
import com.silvertaurus.trade_go_kmp.presentation.component.ChartView
import com.silvertaurus.trade_go_kmp.presentation.component.IntervalSelector
import com.silvertaurus.trade_go_kmp.presentation.viewmodel.DetailViewModel
import org.koin.compose.koinInject

class DetailScreen(
    private val context: com.arkivanov.decompose.ComponentContext,
    private val coinId: String,
    private val onBack: () -> Unit
) {
    @Composable
    fun Render() {
        val getDetail: GetCoinDetailUseCase = koinInject()
        val getHistory: GetPriceHistoryUseCase = koinInject()
        val vm = remember { DetailViewModel(coinId, getDetail, getHistory) }

        DetailContent(vm = vm, onBack = onBack)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(vm: DetailViewModel, onBack: () -> Unit) {
    val detail by vm.coin.collectAsState()
    val history by vm.history.collectAsState()
    val interval by vm.interval.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(detail?.symbol ?: "Loading...", color = Color(0xFF00FF87)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0B0B0B)
                )
            )
        },
        containerColor = Color(0xFF0B0B0B),
        contentColor = Color(0xFF00FF87)
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header info
            detail?.let {
                Text("${it.name} (${it.symbol})", style = MaterialTheme.typography.titleLarge)
                Text("Price: $${it.priceUsd}", style = MaterialTheme.typography.bodyLarge)
                Text("Change: ${it.changePercent24Hr}%", style = MaterialTheme.typography.bodySmall)
            }

            Spacer(Modifier.height(16.dp))

            // Interval tabs
            IntervalSelector(
                selected = interval,
                onSelect = { vm.onIntervalChanged(it) }
            )

            Spacer(Modifier.height(8.dp))

            // Chart
            if (history.isNotEmpty()) {
                ChartLineView(
                    data = history,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )
            } else {
                Text("Loading chart...", color = Color.Gray)
            }
        }
    }
}
