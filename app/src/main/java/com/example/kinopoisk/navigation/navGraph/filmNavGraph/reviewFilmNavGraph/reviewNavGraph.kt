package com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.FILM_ID_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.constants.ReviewFilmScreenConstants.Argument.REVIEW_ID_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.constants.ReviewFilmScreenConstants.Route.REVIEW_ROUTE
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.constants.ReviewFilmScreenRoute
import com.example.kinopoisk.screen.review.ReviewDetailScreen

fun NavGraphBuilder.reviewFilmNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = ReviewFilmScreenRoute.ReviewDetail.route,
        route = REVIEW_ROUTE,
        builder = {
            composable(
                ReviewFilmScreenRoute.ReviewDetail.route,
                arguments = listOf(
                    navArgument(REVIEW_ID_ARGUMENT){
                        type = NavType.StringType
                    }, navArgument(FILM_ID_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                ReviewDetailScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    reviewId = it.arguments?.getString(REVIEW_ID_ARGUMENT)!!.toInt(),
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt()
                )
            }
        }
    )
}