package com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core_utils.navigation.settingNavGraph.SettingScreenConstants.Route.SETTING_ROUTE
import com.example.core_utils.navigation.settingNavGraph.SettingScreenRoute
import com.example.feature_settings.screen.SettingUserScreen
import com.example.kinopoisk.di.AppComponent

fun NavGraphBuilder.settingNavGraph(
    navController: NavController,
    appComponent: AppComponent
) {
    navigation(
        startDestination = SettingScreenRoute.SettingUser.route,
        route = SETTING_ROUTE,
        builder = {
            composable(SettingScreenRoute.SettingUser.route){
                SettingUserScreen(
                    navController = navController,
                    settingViewModel = appComponent.settingViewModel()
                )
            }
        }
    )
}