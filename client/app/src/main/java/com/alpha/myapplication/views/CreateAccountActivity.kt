package com.alpha.myapplication.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alpha.myapplication.components.AlphaPrimaryButton
import com.alpha.myapplication.components.AlphaTextField
import com.alpha.myapplication.routes.LoginFormRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountActivity(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var repeatedPassword by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF171821)
                ),
                title = {
                    Text("Create Account")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { it ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = Color(0xFF171821)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
            ) {
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp),
                )
                AlphaTextField(
                    value = username,
                    placeholder = "Your Username",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    username = it
                }

                Text(
                    text = "Password",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp)
                )

                AlphaTextField(
                    value = password,
                    placeholder = "Your Password",
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    password = it
                }

                Text(
                    text = "Confirm Password",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp)
                )

                AlphaTextField(
                    value = repeatedPassword,
                    placeholder = "Confirm your password",
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeatedPassword = it
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    AlphaPrimaryButton(title = "Create Account") {
                        navController.navigate(LoginFormRoute)
                    }
                }
            }
        }
    }
}