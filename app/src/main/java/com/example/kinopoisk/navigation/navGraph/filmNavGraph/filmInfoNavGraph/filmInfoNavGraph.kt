package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.constants.CinemaScreenConstants.Argument.CINEMA_ID_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenConstants.Argument.FILM_ID_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenConstants.Argument.KEY_WEB_SCREEN_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenConstants.Argument.WEB_URL_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenConstants.Route.FILM_INFO_ROUTE
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenRoute
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmMoreNavGraph.filmMoreNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.reviewFilmNavGraph
import com.example.kinopoisk.screen.filmInfo.FilmInfoScreen
import com.example.kinopoisk.screen.filmInfo.SerialInfoSeasonScreen
import com.example.kinopoisk.screen.filmInfo.WebScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.filmInfoNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
) {
    navigation(
        startDestination = FilmScreenRoute.FilmInfo.route,
        route = FILM_INFO_ROUTE,
        builder = {
            reviewFilmNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            filmMoreNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            composable(
                FilmScreenRoute.FilmInfo.route,
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
                FilmScreenRoute.SerialInfoSeason.route,
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
                FilmScreenRoute.WebScreen.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }, navArgument(KEY_WEB_SCREEN_ARGUMENT){
                        type = NavType.StringType
                    }, navArgument(WEB_URL_ARGUMENT){
                        type = NavType.StringType
                    }, navArgument(CINEMA_ID_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ){
                WebScreen(
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT),
                    keyWebScreen = it.arguments?.getString(KEY_WEB_SCREEN_ARGUMENT).toString(),
                    webUrl = it.arguments?.getString(WEB_URL_ARGUMENT).toString(),
                    cinemaId = it.arguments?.getString(CINEMA_ID_ARGUMENT)?.toInt()
                )
            }
        }
    )
}