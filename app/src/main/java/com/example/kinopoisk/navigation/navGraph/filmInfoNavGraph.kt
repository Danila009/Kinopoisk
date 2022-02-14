package com.example.kinopoisk.navigation.navGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.FILM_ID_ARGUMENT
import com.example.kinopoisk.navigation.FILM_INFO_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmInfo.FilmInfoScreen
import com.example.kinopoisk.screen.filmInfo.SerialInfoSeasonScreen

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
                    lifecycleScope = lifecycleScope
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
        }
    )
}