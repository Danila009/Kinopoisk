package com.example.core_utils.navigation.mainNavGraph

sealed class MainScreenRoute {
    sealed class MainRoute(val route:String){
        object Home: MainRoute("main_home_screen")
        object Films: MainRoute("main_films_screen")
        object Search: MainRoute("main_search_screen")
        object Profile: MainRoute("main_profile_screen")
    }
}