package com.alpha.myapplication.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpha.myapplication.R
import com.alpha.myapplication.dataStore
import com.alpha.myapplication.factories.SplashViewModelFactory
import com.alpha.myapplication.routes.HomeRoute
import com.alpha.myapplication.routes.LoginFormRoute
import com.alpha.myapplication.types.SplashStates
import com.alpha.myapplication.viewModel.SplashViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashView(
    navController: NavController,
    splashViewModel: SplashViewModel = viewModel(factory = SplashViewModelFactory(LocalContext.current.dataStore))
) {
    val splashState by splashViewModel.splashState.collectAsState()

    LaunchedEffect(Unit) {
        splashViewModel.isValidToken()
    }

    LaunchedEffect(splashState) {
        when(splashState) {
            SplashStates.LOADING -> { }
            SplashStates.LOGIN -> navController.navigate(LoginFormRoute)
            SplashStates.HOME -> navController.navigate(HomeRoute)
        }
    }

    Surface(
        color = Color(0xFF171821),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(200.dp)
            )

            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = Color(0xFF831DF5),
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}