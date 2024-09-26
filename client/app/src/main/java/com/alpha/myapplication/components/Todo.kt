package com.alpha.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Todo() {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(
                RoundedCornerShape(size = 10.dp)
            )
            .fillMaxWidth()
            .background(color = Color(0xFF21222D))
    ){
        Checkbox(checked = false, onCheckedChange = {  })
        Text(text = "Todo Title")
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete todo")
        }
    }
}