package com.alpha.myapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alpha.myapplication.R
import com.alpha.myapplication.components.AlphaPrimaryButton
import com.alpha.myapplication.components.AlphaTextField
import com.alpha.myapplication.routes.CreateAccountRoute
import com.alpha.myapplication.routes.HomeRoute

@Composable
fun LoginForm(modifier: Modifier = Modifier, navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
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

        AlphaPrimaryButton(
            title = "Log in",
            modifier = modifier.padding(horizontal = 0.dp, vertical = 16.dp)
        ) {
            navController.navigate(HomeRoute)
        }

        TextButton(onClick = {
            navController.navigate(CreateAccountRoute)
        }) {
            Text("Create account", style = MaterialTheme.typography.bodyLarge)
        }
    }
}