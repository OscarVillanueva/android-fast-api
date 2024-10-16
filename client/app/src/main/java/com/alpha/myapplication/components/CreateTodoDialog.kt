package com.alpha.myapplication.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoDialog(
    onCancel: () -> Unit
) {
    var todo by remember {
        mutableStateOf("")
    }
    Surface(
        color = Color(0x80000000)
    ) {
        AlertDialog(onDismissRequest = { /*TODO*/ }) {
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .background(Color(0xFF21222D))
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 8.dp),
                    text = "Add Todo",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )

                AlphaTextField(
                    value = todo,
                    placeholder = "Create todo"
                ) {
                    todo = it
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp, 0.dp, 0.dp)
                ){
                    AlphaSecondaryButton(
                        title = "Cancel",
                        modifier = Modifier.padding(
                            start = 0.dp,
                            top = 0.dp,
                            end = 10.dp,
                            bottom = 0.dp
                        )
                    ){
                        onCancel()
                    }

                    AlphaPrimaryButton(
                        title = "Add"
                    ) {
                    }
                }


            }
        }

    }
}

