package com.alpha.myapplication.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.alpha.myapplication.ui.theme.PurpleGrey40
import com.alpha.myapplication.ui.theme.alphaInput

@Composable
fun AlphaTextField(
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onChange: (String) -> Unit
) {
    TextField(
        colors = alphaInput(),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = visualTransformation,
        value = value,
        modifier = modifier,
        placeholder = {
            Text(text = placeholder, color = Color(156, 163, 175))
        },
        onValueChange = {
            onChange(it)
        }
    )
}