package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenConstants.Argument.ADMIN_FILM_LIST_ITEM_ID
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph.constants.FilmTopScreenConstants.Argument.FILM_ADMIN_LIST_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph.constants.FilmTopScreenConstants.Argument.FILM_TOP_NAME_ARGUMENT
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph.constants.FilmTopScreenConstants.Route.FILM_TOP_ROUTE
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph.constants.FilmTopScreenRoute
import com.example.kinopoisk.screen.filmTop.FilmTopScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListAddScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListItemAddScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListItemScreen
import com.example.kinopoisk.utils.Converters

fun NavGraphBuilder.filmTopNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = FilmTopScreenRoute.FilmTop.route,
        route = FILM_TOP_ROUTE,
        builder = {
            composable(
                FilmTopScreenRoute.FilmTop.route,
                arguments = listOf(
                    navArgument(FILM_TOP_NAME_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                FilmTopScreen(
                    navController = navController,
                    nameTopViewState = Converters().decodeFromString(
                        it.arguments?.getString(FILM_TOP_NAME_ARGUMENT).toString()
                    )
                )
            }

            composable(
                FilmTopScreenRoute.FilmListAdd.route,
                arguments = listOf(
                    navArgument(FILM_ADMIN_LIST_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ){
                val filmList = it.arguments?.getString(FILM_ADMIN_LIST_ARGUMENT)
                FilmListAddScreen(
                    navController = navController,
                    filmListString = filmList?.let { filmList }
                )
            }
            composable(FilmTopScreenRoute.FilmListItemAdd.route){
                FilmListItemAddScreen(
                    navController = navController
                )
            }
            composable(
                FilmTopScreenRoute.AdminListFilmItem.route,
                arguments = listOf(
                    navArgument(ADMIN_FILM_LIST_ITEM_ID){
                        type = NavType.StringType
                    }
                )
            ){
                FilmListItemScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    adminListFilmId = it.arguments?.getString(ADMIN_FILM_LIST_ITEM_ID)!!.toInt()
                )
            }
        }
    )
}