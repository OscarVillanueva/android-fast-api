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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alpha.myapplication.R
import com.alpha.myapplication.controller.SplashController
import com.alpha.myapplication.routes.HomeRoute
import com.alpha.myapplication.routes.LoginFormRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashView(navController: NavController) {
    val controller = SplashController(LocalContext.current)

    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    LaunchedEffect(Unit) {
        scope.launch {
            controller.isValidToken().fold({
                navController.navigate(HomeRoute)
            }, {
                navController.navigate(LoginFormRoute)
            })
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