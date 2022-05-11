package com.example.feature_authorization.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.user.Authorization
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.EmailTextFieldView
import com.example.core_ui.view.PasswordTextFieldView
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.loginNavGraph.LoginScreenRoute
import com.example.feature_authorization.common.validateAuthorization
import com.example.feature_authorization.viewModel.AuthorizationViewModel
import kotlinx.coroutines.flow.onEach

@Composable
fun AuthorizationScreen(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel
) {

    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val authorizationError = remember { mutableStateOf("") }

    authorizationViewModel.responseAuthorizationError.onEach {
        authorizationError.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Authorization")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MAIN_ROUTE") }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = authorizationError.value,
                        modifier = Modifier.padding(5.dp),
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )

                    EmailTextFieldView(
                        label = "Email",
                        value = email
                    )

                    PasswordTextFieldView(
                        label = "Password",
                        value = password
                    )

                    Button(
                        onClick = {
                            if (validateAuthorization(
                                    email = email.value,
                                    password = password.value,
                                    message = authorizationError
                            ) ){
                                authorizationViewModel.authorization(
                                    authorization = Authorization(
                                        email = email.value,
                                        password = password.value
                                    ),
                                    navController = navController
                                )
                            }
                        },
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = secondaryBackground
                        )
                    ) {
                        Text(text = "Authorization")
                    }

                    TextButton(onClick = {
                        navController.navigate(LoginScreenRoute.UpdateUserPassword.route)
                    }) {
                        Text(
                            text = "Забыл пароль",
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.Bold,
                            color = secondaryBackground
                        )
                    }
                }
            }
        }
    )
}