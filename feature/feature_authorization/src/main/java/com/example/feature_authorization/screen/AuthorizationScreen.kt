package com.example.feature_authorization.screen

import androidx.compose.foundation.layout.*
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
import com.example.core_network_domain.authResult.AuthResult
import com.example.core_network_domain.model.user.Authorization
import com.example.core_ui.activityResult.activityResultAuthGoogle
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.EmailTextFieldView
import com.example.core_ui.view.GoogleButton
import com.example.core_ui.view.PasswordTextFieldView
import com.example.core_utils.common.BaseConstants
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.loginNavGraph.LoginScreenRoute
import com.example.feature_authorization.common.validateAuthorization
import com.example.feature_authorization.viewModel.AuthorizationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AuthorizationScreen(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel
) {

    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val secondary = Firebase.app(BaseConstants.PROJECT_ID)

    val auth: FirebaseAuth = Firebase.auth(secondary)

    val authorizationError = remember { mutableStateOf("") }

    val clickedClickableGoogleButton = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val authResultLauncher = activityResultAuthGoogle(
        contract = AuthResult(),
        account = { account ->
            if (account == null){
                authorizationError.value = "account null"
            }else{
                scope.launch {
                    val credentials =
                        GoogleAuthProvider.getCredential(account.idToken, null)

                    auth.signInWithCredential(credentials).await()

                    authorizationViewModel.authorization(
                        authorization = Authorization(
                            email = account.email!!,
                            password = auth.currentUser?.uid!!
                        ), navController = navController,
                        clickedClickableGoogleButton = clickedClickableGoogleButton
                    )
                }
            }
        }, error = {
            clickedClickableGoogleButton.value = false
            authorizationError.value = "Error Google sing in"
        }
    )

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
                    IconButton(onClick = { navController.navigateUp() }) {
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
                                    navController = navController,
                                    clickedClickableGoogleButton = clickedClickableGoogleButton
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

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                    )

                    GoogleButton(
                        modifier = Modifier.padding(5.dp),
                        clickedClickable = clickedClickableGoogleButton
                    ) {
                        authResultLauncher.launch(BaseConstants.REQUEST_CODE_SIGN_IN)
                    }
                }
            }
        }
    )
}