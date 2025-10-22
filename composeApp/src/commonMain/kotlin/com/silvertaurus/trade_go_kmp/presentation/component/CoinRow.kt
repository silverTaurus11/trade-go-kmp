package com.silvertaurus.trade_go_kmp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.silvertaurus.trade_go_kmp.domain.model.Coin

@Composable
fun CoinRow(
    coin: Coin,
    isFavorite: Boolean,
    onSelect: (String) -> Unit,
    onToggle: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(coin.id) }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("${coin.name} (${coin.symbol})", color = Color.White)
            Text(
                "Price: $${coin.priceUsd}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
        IconButton(onClick = { onToggle(coin.id) }) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = null,
                tint = Color.Yellow
            )
        }
    }
}