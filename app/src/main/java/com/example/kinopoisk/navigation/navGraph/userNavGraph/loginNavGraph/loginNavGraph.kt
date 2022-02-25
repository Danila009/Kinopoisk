package com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph.constants.LoginScreenConstants.Route.LOGIN_ROUTE
import com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph.constants.LoginScreenRoute
import com.example.kinopoisk.screen.login.AuthorizationScreen
import com.example.kinopoisk.screen.login.RegistrationScreen
import com.example.kinopoisk.screen.login.UpdateUserPasswordScreen

fun NavGraphBuilder.loginNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = LoginScreenRoute.Authorization.route,
        route = LOGIN_ROUTE,
        builder = {
            composable(LoginScreenRoute.Authorization.route){
                AuthorizationScreen(
                    navController = navController,
                )
            }
            composable(LoginScreenRoute.Registration.route){
                RegistrationScreen(
                    navController = navController
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