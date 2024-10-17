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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alpha.myapplication.components.AlphaErrorLabel
import com.alpha.myapplication.components.AlphaPrimaryButton
import com.alpha.myapplication.components.AlphaTextField
import com.alpha.myapplication.factories.CreateAccountVMFactory
import com.alpha.myapplication.routes.LoginFormRoute
import com.alpha.myapplication.viewModel.CreateAccountViewModel
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun CreateAccountActivity(
    navController: NavController,
    createAccountViewModel: CreateAccountViewModel = viewModel(factory = CreateAccountVMFactory())
) {

    val validationSchema by createAccountViewModel.validationSchema.collectAsState()

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
                    createAccountViewModel.onUsernameChange(it)
                }

                if (!validationSchema.isUsernameValid.first &&
                    validationSchema.isUsernameValid.second != "not touched")
                    AlphaErrorLabel(title = validationSchema.isUsernameValid.second)

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
                    createAccountViewModel.onPasswordChange(it)
                }

                if (!validationSchema.isPasswordValid.first &&
                    validationSchema.isPasswordValid.second != "not touched")
                    AlphaErrorLabel(title = validationSchema.isPasswordValid.second)

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
                    createAccountViewModel.onConfirmationChange(
                        pwd = password,
                        confirm = it
                    )
                }

                if (!validationSchema.isConfirmationValid.first &&
                    validationSchema.isConfirmationValid.second != "not touched")
                    AlphaErrorLabel(title = validationSchema.isConfirmationValid.second)

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AlphaPrimaryButton(
                        enabled = validationSchema.isValidAccount,
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