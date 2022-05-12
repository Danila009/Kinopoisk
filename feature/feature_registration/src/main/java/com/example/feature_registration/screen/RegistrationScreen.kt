package com.example.feature_registration.screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.authResult.AuthResult
import com.example.core_network_domain.common.Constants.REQUEST_CODE_SIGN_IN
import com.example.core_network_domain.model.user.Registration
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.BaseTextFieldView
import com.example.core_ui.view.EmailTextFieldView
import com.example.core_ui.view.GoogleButton
import com.example.core_ui.view.PasswordTextFieldView
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_registration.common.validateRegistration
import com.example.feature_registration.viewModel.RegistrationViewModel
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    navController: NavController,
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val auth: FirebaseAuth = Firebase.auth

    val registrationError = remember { mutableStateOf("") }

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResult()) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)

            val credentials = GoogleAuthProvider.getCredential(account?.idToken, null)

            auth.signInWithCredential(credentials)

            if (account == null){
                Log.e("GoogleSingIn:", "account null")
                Log.e("GoogleSingIn:", auth.currentUser.toString())
            }else{
                scope.launch {
                    registrationViewModel.registration(
                        registration = Registration(
                            username = account.displayName!!,
                            email = account.email!!,
                            password = account.idToken!!,
                            photo = null
                        ), navController = navController
                    )
                }
            }
        }catch (e:Exception){
            Log.e("GoogleSingIn:", e.message.toString())
        }
    }

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

                    GoogleButton(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        authResultLauncher.launch(REQUEST_CODE_SIGN_IN)
                    }
                }
            }
        }
    )
}