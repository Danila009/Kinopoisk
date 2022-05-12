package com.example.feature_update_user_password.screen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.EmailTextFieldView
import com.example.core_ui.view.PasswordTextFieldView
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.loginNavGraph.LoginScreenRoute
import com.example.feature_update_user_password.validate.validateAuthorization
import com.example.feature_update_user_password.viewModel.UpdateUserPasswordViewModel
import kotlinx.coroutines.flow.onEach

@Composable
fun UpdateUserPasswordScreen(
    navController: NavController,
    updateUserPasswordViewModel: UpdateUserPasswordViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    val message = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    updateUserPasswordViewModel.responseMessageUpdatePassword.onEach {
        message.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Update password")
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(LoginScreenRoute.Authorization.route) }) {
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
                    Text(
                        text = message.value,
                        modifier = Modifier.padding(5.dp),
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )

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
                            message = message
                        )){
                            updateUserPasswordViewModel.patchUpdatePassword(
                                email = email.value,
                                password = password.value,
                            )
                            if (message.value.isNotEmpty()){
                                navController.navigate(LoginScreenRoute.Authorization.route)
                                Toast.makeText(context, "Пароль изменен", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }) {
                        Text(text = "Update password")
                    }
                }
            }
        }
    )
}