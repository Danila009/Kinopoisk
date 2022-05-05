package com.example.core_utils.navigation.loginNavGraph

sealed class LoginScreenRoute(val route:String) {
    object Authorization: LoginScreenRoute("authorization_screen")
    object Registration: LoginScreenRoute("registration_screen")
    object UpdateUserPassword:LoginScreenRoute("update_user_password_screen")
}