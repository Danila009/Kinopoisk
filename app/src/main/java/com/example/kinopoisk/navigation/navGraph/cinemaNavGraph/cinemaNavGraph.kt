package com.example.kinopoisk.navigation.navGraph.cinemaNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenConstants.Argument.CINEMA_ID_ARGUMENT
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenConstants.Route.CINEMA_ROUTE
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.kinopoisk.screen.cinema.AddReviewCinemaScreen
import com.example.kinopoisk.screen.cinema.CinemaInfoScreen
import com.example.kinopoisk.screen.cinema.CinemaMapScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.cinemaNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = CinemaScreenRoute.CinemaInfo.route,
        route = CINEMA_ROUTE,
        builder = {
            composable(CinemaScreenRoute.CinemaMap.route){
                CinemaMapScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope
                )
            }
            composable(
                CinemaScreenRoute.CinemaInfo.route,
                arguments = listOf(
                    navArgument(
                        name = CINEMA_ID_ARGUMENT
                    ){
                        type = NavType.IntType
                    }
                )
            ){
                CinemaInfoScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    cinemaId = it.arguments?.getInt(CINEMA_ID_ARGUMENT)!!.toInt()
                )
            }
            composable(
                CinemaScreenRoute.AddReviewCinema.route,
                arguments = listOf(
                    navArgument(
                        name = CINEMA_ID_ARGUMENT
                    ){
                        type = NavType.IntType
                    }
                )
            ){
                AddReviewCinemaScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    cinemaId = it.arguments?.getInt(CINEMA_ID_ARGUMENT)!!.toInt()
                )
            }
        }
    )
}