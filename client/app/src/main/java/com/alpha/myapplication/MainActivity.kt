package com.alpha.myapplication

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alpha.myapplication.routes.CreateAccountRoute
import com.alpha.myapplication.routes.HomeRoute
import com.alpha.myapplication.routes.LoginFormRoute
import com.alpha.myapplication.routes.SplashViewRoute
import com.alpha.myapplication.ui.theme.MyApplicationTheme
import com.alpha.myapplication.views.CreateAccountActivity
import com.alpha.myapplication.views.HomeView
import com.alpha.myapplication.views.LoginForm
import com.alpha.myapplication.views.SplashView

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = SplashViewRoute,
                    enterTransition = {
                        EnterTransition.None
                    },
                    exitTransition = {
                        ExitTransition.None
                    }
                ) {
                    composable<SplashViewRoute> {
                        SplashView(navController = navController)
                    }
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