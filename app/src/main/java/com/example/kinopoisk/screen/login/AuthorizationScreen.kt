package com.example.kinopoisk.screen.login

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.user.Authorization
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.login.validate.validateAuthorization
import com.example.kinopoisk.screen.login.view.EmailTextFieldView
import com.example.kinopoisk.screen.login.view.PasswordTextFieldView
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun AuthorizationScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        Text(text = "Authorization")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Screen.Main.route) }) {
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
                                    context = context
                            ) ){
                                loginViewModel.postAuthorization(
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

                    TextButton(onClick = { navController.navigate(Screen.UpdateUserPassword.route) }) {
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