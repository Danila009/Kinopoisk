package com.example.kinopoisk.navigation.navGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.CINEMA_ID_ARGUMENT
import com.example.kinopoisk.navigation.CINEMA_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.cinema.CinemaInfoScreen
import com.example.kinopoisk.screen.cinema.CinemaMapScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.cinemaNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = Screen.CinemaInfo.route,
        route = CINEMA_ROUTE,
        builder = {
            composable(Screen.CinemaMap.route){
                CinemaMapScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope
                )
            }
            composable(
                Screen.CinemaInfo.route,
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
        }
    )
}