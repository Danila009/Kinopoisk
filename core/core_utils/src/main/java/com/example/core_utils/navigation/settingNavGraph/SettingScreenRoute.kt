package com.example.core_utils.navigation.settingNavGraph

sealed class SettingScreenRoute(val route:String){
    object SettingUser: SettingScreenRoute("setting_user_screen")
}
