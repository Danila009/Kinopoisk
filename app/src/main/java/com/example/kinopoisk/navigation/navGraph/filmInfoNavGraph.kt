package com.example.kinopoisk.navigation.navGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.*
import com.example.kinopoisk.screen.filmInfo.FilmInfoScreen
import com.example.kinopoisk.screen.filmInfo.SerialInfoSeasonScreen
import com.example.kinopoisk.screen.filmInfo.WebScreen

fun NavGraphBuilder.filmInfoNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
) {
    navigation(
        startDestination = Screen.FilmInfo.route,
        route = FILM_INFO_ROUTE,
        builder = {
            composable(
                Screen.FilmInfo.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                FilmInfoScreen(
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt(),
                    lifecycleScope = lifecycleScope,
                )
            }
            composable(
                Screen.SerialInfoSeason.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ) {
                SerialInfoSeasonScreen(
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt(),
                    lifecycleScope = lifecycleScope
                )
            }
            composable(
                Screen.WebScreen.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }, navArgument(KEY_SCREEN_ARGUMENT){
                        type = NavType.StringType
                    }, navArgument(WEB_URL_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                WebScreen(
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT),
                    keyScreen = it.arguments?.getString(KEY_SCREEN_ARGUMENT).toString(),
                    webUrl = it.arguments?.getString(WEB_URL_ARGUMENT).toString()
                )
            }
        }
    )
}