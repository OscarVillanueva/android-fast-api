package com.alpha.myapplication.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpha.myapplication.components.CreateTodoDialog
import com.alpha.myapplication.components.Todo
import com.alpha.myapplication.dataStore
import com.alpha.myapplication.factories.HomeViewModelFactory
import com.alpha.myapplication.routes.LoginFormRoute
import com.alpha.myapplication.types.HomeStates
import com.alpha.myapplication.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(LocalContext.current.dataStore))
) {

    val homeState by homeViewModel.homeState.collectAsState()

    var showCreateDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(homeState) {
        if (homeState == HomeStates.LOG_OUT) {
            navController.navigate(LoginFormRoute)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF21222D)
                ),
                title = {
                    Text(
                        text = "Toddap",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    IconButton(onClick = { homeViewModel.logOut() }) {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        }
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = Color(0xFF171821)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Todo()
                Todo()
                Todo()
            }

            if (showCreateDialog)
                CreateTodoDialog(
                    onCancel = { showCreateDialog = false }
                )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {
                FloatingActionButton(
                    containerColor = Color(0xFF831DF5),
                    onClick = { showCreateDialog = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Todo")
                }
            }
        }
    }
}