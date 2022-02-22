package com.example.kinopoisk.navigation.navGraph.userNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kinopoisk.navigation.SETTING_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.setting.SettingUserScreen

fun NavGraphBuilder.settingNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.SettingUser.route,
        route = SETTING_ROUTE,
        builder = {
            composable(Screen.SettingUser.route){
                SettingUserScreen(
                    navController = navController
                )
            }
        }
    )
}