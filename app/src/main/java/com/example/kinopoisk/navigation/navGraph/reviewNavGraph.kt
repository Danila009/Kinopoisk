package com.example.kinopoisk.navigation.navGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.FILM_ID_ARGUMENT
import com.example.kinopoisk.navigation.REVIEW_ID_ARGUMENT
import com.example.kinopoisk.navigation.REVIEW_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.review.ReviewDetailScreen

fun NavGraphBuilder.reviewNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = Screen.ReviewDetail.route,
        route = REVIEW_ROUTE,
        builder = {
            composable(
                Screen.ReviewDetail.route,
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