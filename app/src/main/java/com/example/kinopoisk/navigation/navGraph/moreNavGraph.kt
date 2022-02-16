package com.example.kinopoisk.navigation.navGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.FILM_ID_ARGUMENT
import com.example.kinopoisk.navigation.MORE_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.more.ImageMoreScreen
import com.example.kinopoisk.screen.more.ReviewMoreScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.moreNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = Screen.ImageMore.route,
        route = MORE_ROUTE,
        builder = {
            composable(
                route = Screen.ImageMore.route,
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
                route = Screen.ReviewMore.route,
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