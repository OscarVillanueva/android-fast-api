package com.alpha.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.alpha.myapplication.ui.theme.MyApplicationTheme
import com.alpha.myapplication.ui.theme.alphaInput
import com.alpha.myapplication.ui.theme.alphaPrimaryButton
import com.alpha.myapplication.views.CreateAccountActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "Login",
                    enterTransition = {
                        EnterTransition.None
                    },
                    exitTransition = {
                        ExitTransition.None
                    }
                ) {
                    composable("Login") {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color(0xFF171821)
                        ) {
                            LoginForm(navController = navController)
                        }
                    }
                    composable("CreateAccount") {
                        CreateAccountActivity(navController)
                    }
                }

            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier, navController: NavController) {
    val localContext = LocalContext.current

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
        TextField(
            colors = alphaInput(),
            shape = RoundedCornerShape(10.dp),
            value = username,
            onValueChange = { username = it }
        )

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
        TextField(
            shape = RoundedCornerShape(10.dp),
            colors = alphaInput(),
            visualTransformation = PasswordVisualTransformation(),
            value = password,
            onValueChange = { password = it }
        )

        Button(
            modifier = modifier.padding(horizontal = 0.dp, vertical = 16.dp),
            colors = alphaPrimaryButton(),
            onClick = {  }) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.titleLarge
            )
        }

        TextButton(onClick = {
            navController.navigate("CreateAccount")
        }) {
            Text("Create account", style = MaterialTheme.typography.bodyLarge)
        }
    }
}