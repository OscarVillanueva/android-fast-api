package com.alpha.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alpha.myapplication.routes.CreateAccountRoute
import com.alpha.myapplication.routes.HomeRoute
import com.alpha.myapplication.routes.LoginFormRoute
import com.alpha.myapplication.ui.theme.MyApplicationTheme
import com.alpha.myapplication.views.CreateAccountActivity
import com.alpha.myapplication.views.HomeView
import com.alpha.myapplication.views.LoginForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginFormRoute,
                    enterTransition = {
                        EnterTransition.None
                    },
                    exitTransition = {
                        ExitTransition.None
                    }
                ) {
                    composable<LoginFormRoute> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color(0xFF171821)
                        ) {
                            LoginForm(navController = navController)
                        }
                    }
                    composable<CreateAccountRoute> {
                        CreateAccountActivity(navController = navController)
                    }
                    composable<HomeRoute> {
                        HomeView(navController = navController)
                    }
                }

            }
        }
    }
}