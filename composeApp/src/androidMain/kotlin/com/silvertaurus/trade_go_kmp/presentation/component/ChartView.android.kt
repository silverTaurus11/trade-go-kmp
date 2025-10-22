package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint

@Composable
actual fun ChartView(
    data: List<PriceHistoryPoint>,
    modifier: Modifier
) {
    val lineColor = Color(0xFF00FF87)

    if (data.isEmpty()) return

    Canvas(modifier = modifier.fillMaxWidth()) {
        val maxY = data.maxOf { it.priceUsd }.toFloat()
        val minY = data.minOf { it.priceUsd }.toFloat()
        val range = maxY - minY
        val stepX = size.width / (data.size - 1)
        val path = Path()

        data.forEachIndexed { index, point ->
            val x = index * stepX
            val normalizedY = (point.priceUsd - minY) / range
            val y = size.height - (normalizedY * size.height)
            if (index == 0) path.moveTo(x, y.toFloat()) else path.lineTo(x, y.toFloat())
        }

        // Draw main line
        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Optional: draw gradient fill
        drawPath(
            path = path.apply {
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            },
            brush = Brush.verticalGradient(
                colors = listOf(lineColor.copy(alpha = 0.4f), Color.Transparent)
            )
        )
    }
}