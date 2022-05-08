package com.example.kinopoisk.navigation.navGraph.mainNavGraph.sortingFilmNavGraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.feature_films.screen.FilmsScreen
import com.example.kinopoisk.di.AppComponent
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.COUNTRIES_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.COUNTRIES_ID_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.GENRE_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.GENRE_ID_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.ORDER_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.RATING_FROM_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.RATING_TO_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.TYPE_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.YEAR_FROM_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Argument.YEAR_TO_ARGUMENT
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenConstants.Route.SORTING_FILM_ROUTE
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenRoute
import com.example.feature_countries.screen.CountriesScreen
import com.example.feature_genre.screen.GenreScreen
import com.example.feature_sorting.screen.SortingScreen
import com.example.kinopoisk.utils.Converters

@ExperimentalFoundationApi
fun NavGraphBuilder.sortingFilmNavGraph(
    navController: NavController,
    appComponent: AppComponent
) {
    navigation(
        startDestination = SortingScreenRoute.SortingFilm.route,
        route = SORTING_FILM_ROUTE,
        builder = {
            composable(
                SortingScreenRoute.SortingFilm.route,
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
                    sortingViewModel = appComponent.sortingViewModel(),
                    genreString = it.arguments?.getString(GENRE_ARGUMENT).toString(),
                    countriesString = it.arguments?.getString(COUNTRIES_ARGUMENT).toString()
                )
            }
            composable(
                SortingScreenRoute.ResultSorting.route,
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
                    navController = navController,
                    filmsViewMode = appComponent.filmsViewModel()
                )
            }
            composable(SortingScreenRoute.Genre.route){
                GenreScreen(
                    navController = navController,
                    genreViewModel = appComponent.genreViewModel()
                )
            }
            composable(SortingScreenRoute.Countries.route){
                CountriesScreen(
                    navController = navController,
                    countriesViewModel = appComponent.countriesViewModel()
                )
            }
        }
    )
}