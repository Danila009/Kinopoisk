package com.example.kinopoisk.navigation.navGraph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.FILM_TOP_NAME_ARGUMENT
import com.example.kinopoisk.navigation.FILM_TOP_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmTop.FilmTopScreen
import com.example.kinopoisk.utils.Converters

fun NavGraphBuilder.filmTopNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.FilmTop.route,
        route = FILM_TOP_ROUTE,
        builder = {
            composable(
                Screen.FilmTop.route,
                arguments = listOf(
                    navArgument(FILM_TOP_NAME_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                FilmTopScreen(
                    navController = navController,
                    nameTopViewState = Converters().decodeFromString(
                        it.arguments?.getString(FILM_TOP_NAME_ARGUMENT).toString()
                    )
                )
            }
        }
    )
}