package com.alpha.myapplication.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alpha.myapplication.ui.theme.alphaPrimaryButton

@Composable
fun AlphaPrimaryButton(
    title: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        modifier = modifier,
        colors = alphaPrimaryButton(),
        onClick = { onClick() }) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}