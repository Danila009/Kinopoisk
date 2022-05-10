package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmMoreNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.filmNavGraph.filmMoreNavGraph.FilmMoreConstants.Argument.FILM_ID_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.filmMoreNavGraph.FilmMoreConstants.Route.MORE_ROUTE
import com.example.core_utils.navigation.filmNavGraph.filmMoreNavGraph.FilmMoreScreenRoute
import com.example.kinopoisk.screen.more.ImageMoreScreen
import com.example.kinopoisk.screen.more.ReviewMoreScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.filmMoreNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = FilmMoreScreenRoute.ImageFilmMore.route,
        route = MORE_ROUTE,
        builder = {
            composable(
                route = FilmMoreScreenRoute.ImageFilmMore.route,
                arguments = listOf(
                navArgument(FILM_ID_ARGUMENT){
                    type = NavType.StringType
                }
            )){
                ImageMoreScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt()
                )
            }
            composable(
                route = FilmMoreScreenRoute.ReviewFilmMore.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                ReviewMoreScreen(
                    lifecycleScope = lifecycleScope,
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt()
                )
            }
        }
    )
}