package com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph.constants.SettingScreenConstants.Route.SETTING_ROUTE
import com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph.constants.SettingScreenRoute
import com.example.kinopoisk.screen.setting.SettingUserScreen

fun NavGraphBuilder.settingNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = SettingScreenRoute.SettingUser.route,
        route = SETTING_ROUTE,
        builder = {
            composable(SettingScreenRoute.SettingUser.route){
                SettingUserScreen(
                    navController = navController
                )
            }
        }
    )
}