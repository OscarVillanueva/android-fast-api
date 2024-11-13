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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.alpha.myapplication.ui.theme.Fonts.Montserrat

@Composable
fun Todo(
    id: Int,
    title: String,
    checked: Boolean,
    isLoading: Boolean,
    onDelete: (id: Int) -> Unit,
    onCheckChange: (id: Int, value: Boolean) -> Unit
) {
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
        Checkbox(
            enabled = !isLoading,
            checked = checked,
            onCheckedChange = { value -> onCheckChange(id, value) }
        )
        Text(
            text = title,
            style = TextStyle(
                fontFamily = Montserrat.getFont(),
                textDecoration =
                if (checked) TextDecoration.LineThrough
                else TextDecoration.None)
        )
        IconButton(
            enabled = !isLoading,
            onClick = { onDelete(id) }
        ) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete todo")
        }
    }
}