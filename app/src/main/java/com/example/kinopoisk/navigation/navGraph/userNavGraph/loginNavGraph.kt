package com.example.kinopoisk.navigation.navGraph.userNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kinopoisk.navigation.LOGIN_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.login.AuthorizationScreen
import com.example.kinopoisk.screen.login.RegistrationScreen

fun NavGraphBuilder.loginNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Authorization.route,
        route = LOGIN_ROUTE,
        builder = {
            composable(Screen.Authorization.route){
                AuthorizationScreen(
                    navController = navController,
                )
            }
            composable(Screen.Registration.route){
                RegistrationScreen(
                    navController = navController
                )
            }
        }
    )
}