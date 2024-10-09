package com.alpha.myapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpha.myapplication.R
import com.alpha.myapplication.components.AlphaPrimaryButton
import com.alpha.myapplication.components.AlphaTextField
import com.alpha.myapplication.dataStore
import com.alpha.myapplication.factories.LoginViewModelFactory
import com.alpha.myapplication.routes.CreateAccountRoute
import com.alpha.myapplication.routes.HomeRoute
import com.alpha.myapplication.types.LoginStates
import com.alpha.myapplication.viewModel.LoginViewModel

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(LocalContext.current.dataStore))
) {

    val loginState by loginViewModel.loginState.collectAsState()

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    LaunchedEffect(loginState) {
        if (loginState == LoginStates.SUCCESS) {
            navController.navigate(HomeRoute)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.size(100.dp))

        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "App Icon",
            modifier = modifier
                .size(200.dp)
        )

        Text(text = "Username", style = MaterialTheme.typography.titleLarge)
        AlphaTextField(value = username, placeholder = "Your Username"){ username = it }

        Text(
            modifier = Modifier.padding(
                start = 0.dp,
                top = 16.dp,
                end = 0.dp,
                bottom = 5.dp
            ),
            text = "Password",
            style = MaterialTheme.typography.titleLarge
        )
        AlphaTextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            placeholder = "Your Password"
        ) {
            password = it
        }

        when (loginState) {
            LoginStates.LOADING -> {
                Spacer(modifier = Modifier.height(16.dp))
                
                CircularProgressIndicator(
                    modifier = Modifier.width(44.dp),
                    color = Color(0xFF831DF5),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            else -> {
                AlphaPrimaryButton(
                    title = "Log in",
                    modifier = modifier.padding(horizontal = 0.dp, vertical = 16.dp)
                ) {
                    loginViewModel.login(username, password)
                }
            }
        }

        TextButton(onClick = {
            navController.navigate(CreateAccountRoute)
        }) {
            Text("Create account", style = MaterialTheme.typography.bodyLarge)
        }

    }
}