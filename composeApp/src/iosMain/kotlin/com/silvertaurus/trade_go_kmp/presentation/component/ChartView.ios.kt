package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint

@Composable
actual fun ChartView(
    data: List<PriceHistoryPoint>,
    modifier: Modifier
) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        if (data.isEmpty()) return@Canvas

        val maxY = data.maxOf { it.priceUsd }.toFloat()
        val stepX = size.width / (data.size - 1)
        val path = Path().apply {
            data.forEachIndexed { index, point ->
                val x = index * stepX
                val y = size.height - (point.priceUsd.toFloat() / maxY) * size.height
                if (index == 0) moveTo(x, y) else lineTo(x, y)
            }
        }
        drawPath(
            path = path,
            color = Color(0xFF007AFF),
            style = Stroke(width = 3.dp.toPx())
        )
    }
}