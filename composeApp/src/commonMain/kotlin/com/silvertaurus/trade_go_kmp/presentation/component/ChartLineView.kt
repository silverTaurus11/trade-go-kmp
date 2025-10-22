package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silvertaurus.trade_go_kmp.domain.model.PriceHistoryPoint
import com.silvertaurus.trade_go_kmp.shared.formatPrice

@OptIn(ExperimentalTextApi::class)
@Composable
fun ChartLineView(
    data: List<PriceHistoryPoint>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFF00FF87),
    labelColor: Color = Color(0xFFAAAAAA),
    gridColor: Color = Color(0xFF1F1F1F)
) {
    if (data.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier.fillMaxWidth()) {
        // --- constants ---
        val maxY = data.maxOf { it.priceUsd }.toFloat()
        val minY = data.minOf { it.priceUsd }.toFloat()
        val range = maxY - minY
        val paddingLeft = 70f
        val paddingBottom = 20f  // ✅ tambahan padding bawah
        val stepYCount = 4

        val chartHeight = size.height - paddingBottom
        val chartWidth = size.width - paddingLeft

        // --- Draw Y labels & grid ---
        for (i in 0..stepYCount) {
            val ratio = i / stepYCount.toFloat()
            val y = chartHeight - (ratio * chartHeight)
            val value = minY + (range * ratio)

            // grid line
            drawLine(
                color = gridColor,
                start = Offset(paddingLeft, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )

            // label (shifted up a little to stay visible)
            drawText(
                textMeasurer = textMeasurer,
                text = formatPrice(value.toDouble()),
                topLeft = Offset(0f, y - 7.dp.toPx()),
                style = TextStyle(color = labelColor, fontSize = 12.sp)
            )
        }

        // --- Draw chart line ---
        val path = Path()
        data.forEachIndexed { index, point ->
            val x = paddingLeft + index * (chartWidth / (data.size - 1))
            val normalizedY = (point.priceUsd - minY) / range
            val y = chartHeight - (normalizedY * chartHeight)
            if (index == 0) path.moveTo(x, y.toFloat()) else path.lineTo(x, y.toFloat())
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
