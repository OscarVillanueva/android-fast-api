package com.alpha.myapplication.views

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alpha.myapplication.components.AlphaErrorLabel
import com.alpha.myapplication.components.AlphaPrimaryButton
import com.alpha.myapplication.components.AlphaTextField
import com.alpha.myapplication.routes.LoginFormRoute
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun CreateAccountActivity(navController: NavController) {

    var isFirstRenderUsername by remember {
        mutableStateOf(true)
    }

    var isUsernameValid by remember {
        mutableStateOf(Pair(false, ""))
    }

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var repeatedPassword by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        snapshotFlow { username }
            .debounce(500L)
            .collect {
                if(isFirstRenderUsername) {
                    isFirstRenderUsername = false
                    return@collect
                }

                isUsernameValid = when {
                    it.trim().isEmpty() -> {
                        Pair(false, "The username is required")
                    }

                    it.length < 5 -> {
                        Pair(false, "The username is not long enough")
                    }

                    else -> {
                        Pair(true, "")
                    }
                }
            }
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

                if (!isUsernameValid.first && isUsernameValid.second.isNotEmpty())
                    AlphaErrorLabel(title = isUsernameValid.second)

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

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AlphaPrimaryButton(
                        enabled = isUsernameValid.first,
                        title = "Create Account"
                    ) {
                        navController.navigate(LoginFormRoute)
                    }

                    TextButton(onClick = { navController.navigate(LoginFormRoute) }) {
                        Text(
                            text = "I have an account",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}