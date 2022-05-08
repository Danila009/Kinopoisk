package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.FILM_ID_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.WEB_URL_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Route.FILM_INFO_ROUTE
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.feature_serial_info.screen.SerialInfoScreen
import com.example.feature_web.screen.WebScreen
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmMoreNavGraph.filmMoreNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.reviewFilmNavGraph
import com.example.kinopoisk.screen.filmInfo.FilmInfoScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.filmInfoNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent
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
                SerialInfoScreen(
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt(),
                    serialInfoViewModel = appComponent.serialInfoViewModel()
                )
            }
            composable(
                route = FilmScreenRoute.WebScreen.route,
                arguments = listOf(
                    navArgument(WEB_URL_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                WebScreen(
                    navController = navController,
                    webUrl = it.arguments?.getString(WEB_URL_ARGUMENT).toString(),
                )
            }
        }
    )
}