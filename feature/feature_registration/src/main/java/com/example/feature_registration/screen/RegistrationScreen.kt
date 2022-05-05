package com.example.feature_registration.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.user.Registration
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.BaseTextFieldView
import com.example.core_ui.view.EmailTextFieldView
import com.example.core_ui.view.PasswordTextFieldView
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_registration.common.validateRegistration
import com.example.feature_registration.viewModel.RegistrationViewModel
import kotlinx.coroutines.flow.onEach

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    navController: NavController
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val registrationError = remember { mutableStateOf("") }

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }

    registrationViewModel.responseRegistrationError.onEach {
        registrationError.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Registration")
                },
                navigationIcon = {
                    IconButton(onClick = {
//                        navController.navigate(MAIN_ROUTE)
                    }) {
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
                        text = registrationError.value,
                        modifier = Modifier.padding(5.dp),
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )

                    BaseTextFieldView(
                        label = "Username",
                        value = username
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
                            if (validateRegistration(
                                    username = username.value,
                                    email = email.value,
                                    password = password.value,
                                    message = registrationError
                                ) ){
                                registrationViewModel.registration(
                                    registration = Registration(
                                        username = username.value,
                                        email = email.value,
                                        password = password.value,
                                        photo = null
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
                        Text(text = "Registration")
                    }
                }
            }
        }
    )
}