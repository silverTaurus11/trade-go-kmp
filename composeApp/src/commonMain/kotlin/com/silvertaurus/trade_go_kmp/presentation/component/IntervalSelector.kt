package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.presentation.viewmodel.Interval

@Composable
fun IntervalSelector(
    selected: Interval,
    onSelect: (Interval) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Interval.entries.forEach { interval ->
            val isSelected = selected == interval
            Button(
                onClick = { onSelect(interval) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFF00FF87) else Color(0xFF1A1A1A),
                    contentColor = if (isSelected) Color.Black else Color(0xFF00FF87)
                )
            ) {
                Text(interval.label)
            }
        }
    }
}