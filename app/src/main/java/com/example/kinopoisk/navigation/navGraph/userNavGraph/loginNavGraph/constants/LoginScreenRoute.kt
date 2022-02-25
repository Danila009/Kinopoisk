package com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph.constants

sealed class LoginScreenRoute(val route:String) {
    object Authorization: LoginScreenRoute("authorization_screen")
    object Registration: LoginScreenRoute("registration_screen")
    object UpdateUserPassword:LoginScreenRoute("update_user_password_screen")
}