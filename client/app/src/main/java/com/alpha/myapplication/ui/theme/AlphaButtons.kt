package com.alpha.myapplication.ui.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun alphaPrimaryButton() = ButtonDefaults.buttonColors(
    containerColor = Color(0xFF831DF5),
)

@Composable
fun alphaSecondaryButton() = ButtonDefaults.buttonColors(
    containerColor = Color(0xFF25262E),
)