package com.example.kinopoisk.navigation.navGraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.kinopoisk.navigation.*
import com.example.kinopoisk.screen.main.MainScreen
import com.example.kinopoisk.screen.main.SortingScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.FilmsScreen

@ExperimentalFoundationApi
fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Main.route,
        route = MAIN_ROUTE,
        builder = {
            composable(Screen.Main.route){
                MainScreen(
                    navController = navController
                )
            }
            composable(Screen.Sorting.route){
                SortingScreen(
                    navController = navController
                )
            }
            composable(
                Screen.ResultSorting.route,
                arguments = listOf(
                    navArgument(RATING_FROM_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(RATING_TO_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(YEAR_FROM_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(YEAR_TO_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                FilmsScreen(
                    ratingFrom = it.arguments?.getString(RATING_FROM_ARGUMENT)!!.toInt(),
                    ratingTo = it.arguments?.getString(RATING_TO_ARGUMENT)!!.toInt(),
                    yearFrom = it.arguments?.getString(YEAR_FROM_ARGUMENT)!!.toInt(),
                    yearTo = it.arguments?.getString(YEAR_TO_ARGUMENT)!!.toInt(),
                    navController = navController
                )
            }
        }
    )
}