package com.example.kinopoisk.navigation.navGraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.kinopoisk.navigation.*
import com.example.kinopoisk.screen.main.MainScreen
import com.example.kinopoisk.screen.main.sortingScreen.SortingScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.FilmsScreen
import com.example.kinopoisk.screen.main.sortingScreen.CountriesScreen
import com.example.kinopoisk.screen.main.sortingScreen.GenreScreen
import com.example.kinopoisk.utils.Converters

@ExperimentalFoundationApi
fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
) {
    navigation(
        startDestination = Screen.Main.route,
        route = MAIN_ROUTE,
        builder = {
            composable(Screen.Main.route){
                MainScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                )
            }
            composable(
                Screen.Sorting.route,
                arguments = listOf(
                    navArgument(GENRE_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(COUNTRIES_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                SortingScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    genreString = it.arguments?.getString(GENRE_ARGUMENT).toString(),
                    countriesString = it.arguments?.getString(COUNTRIES_ARGUMENT).toString()
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
                    },
                    navArgument(TYPE_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(ORDER_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(GENRE_ID_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    },
                    navArgument(COUNTRIES_ID_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ){
                FilmsScreen(
                    ratingFrom = it.arguments?.getString(RATING_FROM_ARGUMENT)!!.toInt(),
                    ratingTo = it.arguments?.getString(RATING_TO_ARGUMENT)!!.toInt(),
                    yearFrom = it.arguments?.getString(YEAR_FROM_ARGUMENT)!!.toInt(),
                    yearTo = it.arguments?.getString(YEAR_TO_ARGUMENT)!!.toInt(),
                    type = it.arguments?.getString(TYPE_ARGUMENT).toString(),
                    order = it.arguments?.getString(ORDER_ARGUMENT).toString(),
                    genres =  Converters().decodeFromString(
                        it.arguments?.getString(GENRE_ID_ARGUMENT).toString()
                    ),
                    countries = Converters().decodeFromString(
                        it.arguments?.getString(COUNTRIES_ID_ARGUMENT).toString()
                    ),
                    navController = navController
                )
            }
            composable(Screen.Genre.route){
                GenreScreen(
                    lifecycleScope = lifecycleScope,
                    navController = navController
                )
            }
            composable(Screen.Countries.route){
                CountriesScreen(
                    lifecycleScope = lifecycleScope,
                    navController = navController
                )
            }

        }
    )
}