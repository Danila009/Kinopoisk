package com.example.kinopoisk.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.login.validate.validateAuthorization
import com.example.kinopoisk.screen.login.view.EmailTextFieldView
import com.example.kinopoisk.screen.login.view.PasswordTextFieldView
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun UpdateUserPasswordScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
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
                    Text(text = "Update password")
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Authorization.route) }) {
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    EmailTextFieldView(
                        label = "Нынешний Email",
                        value = email
                    )

                    PasswordTextFieldView(
                        label = "Update Password",
                        value = password
                    )

                    Button(
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = secondaryBackground
                        ),
                        shape = AbsoluteRoundedCornerShape(20.dp),
                        onClick = {
                        if (validateAuthorization(
                            email = email.value,
                            password = password.value,
                            context = context
                        )){
                            loginViewModel.putUserPassword(
                                email = email.value,
                                password = password.value,
                            )
                            navController.navigate(Screen.Authorization.route)
                            Toast.makeText(context, "Пароль изменен", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text(text = "Update password")
                    }
                }
            }
        }
    )
}