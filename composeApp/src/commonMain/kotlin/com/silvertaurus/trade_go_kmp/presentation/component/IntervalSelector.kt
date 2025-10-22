package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.presentation.theme.BlackBackground
import com.silvertaurus.trade_go_kmp.presentation.theme.GreenPrimary
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
            TextButton(onClick = { onSelect(interval) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        interval.label,
                        color = if (isSelected) GreenPrimary else Color.White
                    )
                    if (isSelected) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            Modifier
                                .height(2.dp)
                                .width(24.dp)
                                .background(GreenPrimary, RoundedCornerShape(1.dp))
                        )
                    }
                }
            }
        }
    }
}