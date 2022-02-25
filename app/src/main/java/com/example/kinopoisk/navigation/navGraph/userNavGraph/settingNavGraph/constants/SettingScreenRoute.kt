package com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph.constants

sealed class SettingScreenRoute(val route:String){
    object SettingUser: SettingScreenRoute("setting_user_screen")
}
