package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint

@Composable
expect fun ChartView(
    data: List<PriceHistoryPoint>,
    modifier: Modifier = Modifier
)