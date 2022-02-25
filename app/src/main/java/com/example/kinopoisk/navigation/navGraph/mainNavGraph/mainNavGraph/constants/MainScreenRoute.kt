package com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants

sealed class MainScreenRoute {
    sealed class MainRoute(val route:String){
        object Main: MainRoute("main_screen")
    }
}