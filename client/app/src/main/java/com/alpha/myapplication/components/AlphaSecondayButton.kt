package com.alpha.myapplication.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alpha.myapplication.ui.theme.alphaSecondaryButton

@Composable
fun AlphaSecondaryButton(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = alphaSecondaryButton(),
        onClick = { onClick() }) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}


