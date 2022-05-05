package com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core_utils.navigation.loginNavGraph.LoginScreenConstants.Route.LOGIN_ROUTE
import com.example.core_utils.navigation.loginNavGraph.LoginScreenRoute
import com.example.feature_authorization.screen.AuthorizationScreen
import com.example.feature_registration.screen.RegistrationScreen
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.screen.login.UpdateUserPasswordScreen

fun NavGraphBuilder.loginNavGraph(
    navController: NavController,
    appComponent: AppComponent
) {
    navigation(
        startDestination = LoginScreenRoute.Authorization.route,
        route = LOGIN_ROUTE,
        builder = {
            composable(LoginScreenRoute.Authorization.route){
                AuthorizationScreen(
                    navController = navController,
                    authorizationViewModel = appComponent.authorizationViewModel()
                )
            }
            composable(LoginScreenRoute.Registration.route){
                RegistrationScreen(
                    navController = navController,
                    registrationViewModel = appComponent.registrationViewModel()
                )
            }
            composable(LoginScreenRoute.UpdateUserPassword.route){
                UpdateUserPasswordScreen(
                    navController = navController
                )
            }
        }
    )
}