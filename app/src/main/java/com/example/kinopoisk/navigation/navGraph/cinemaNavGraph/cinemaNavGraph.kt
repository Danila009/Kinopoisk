package com.example.kinopoisk.navigation.navGraph.cinemaNavGraph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenConstants.Argument.CINEMA_ID_ARGUMENT
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenConstants.Route.CINEMA_ROUTE
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.feature_cinema_add_review.screen.AddReviewCinemaScreen
import com.example.feature_cinema_info.screen.CinemaInfoScreen
import com.example.feature_cinema_map.screen.CinemaMapScreen
import com.example.kinopoisk.di.AppComponent
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
fun NavGraphBuilder.cinemaNavGraph(
    navController: NavController,
    appComponent: AppComponent
) {
    navigation(
        startDestination = CinemaScreenRoute.CinemaInfo.route,
        route = CINEMA_ROUTE,
        builder = {
            composable(CinemaScreenRoute.CinemaMap.route){
                CinemaMapScreen(
                    navController = navController,
                    cinemaViewModel = appComponent.cinemaMapViewModel()
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
                    cinemaInfoViewModel = appComponent.cinemaInfoViewModel(),
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
                    cinemaAddReviewViewModel = appComponent.cinemaAddReviewVideModel(),
                    cinemaId = it.arguments?.getInt(CINEMA_ID_ARGUMENT)!!.toInt()
                )
            }
        }
    )
}