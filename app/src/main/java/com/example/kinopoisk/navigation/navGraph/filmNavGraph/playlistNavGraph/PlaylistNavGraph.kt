package com.example.kinopoisk.navigation.navGraph.filmNavGraph.playlistNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.common.decodeFromString
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.ADMIN_FILM_LIST_ITEM_ID
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.FilmTopScreenConstants.Argument.FILM_ADMIN_LIST_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.FilmTopScreenConstants.Argument.FILM_TOP_NAME_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.FilmTopScreenConstants.Route.FILM_TOP_ROUTE
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.feature_palylist_add.screen.PlaylistAddScreen
import com.example.feature_playlist_add_films.screen.PlaylistAddFilmsScreen
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.screen.filmTop.FilmTopScreen
import com.example.kinopoisk.screen.filmTop.admin.FilmListItemScreen
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
fun NavGraphBuilder.filmTopNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent
) {
    navigation(
        startDestination = PlaylistScreenRoute.Playlist.route,
        route = FILM_TOP_ROUTE,
        builder = {
            composable(
                PlaylistScreenRoute.Playlist.route,
                arguments = listOf(
                    navArgument(FILM_TOP_NAME_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                FilmTopScreen(
                    navController = navController,
                    nameTopViewState = enumValueOf(
                        it.arguments?.getString(FILM_TOP_NAME_ARGUMENT).toString()
                    )
                )
            }

            composable(
                PlaylistScreenRoute.FilmListAdd.route,
                arguments = listOf(
                    navArgument(FILM_ADMIN_LIST_ARGUMENT){
                        nullable = true
                        type = NavType.StringType
                    }
                )
            ){
                val playlistString = it.arguments?.getString(FILM_ADMIN_LIST_ARGUMENT)

                PlaylistAddScreen(
                    playlistAddViewModel = appComponent.playlistAddViewModel(),
                    navController = navController,
                    playlistFilms = if (playlistString != null) decodeFromString(playlistString) else null
                )
            }
            composable(PlaylistScreenRoute.FilmListItemAdd.route){
                PlaylistAddFilmsScreen(
                    navController = navController,
                    playlistAddFilmsViewModel = appComponent.playlistAddFilmsViewModel()
                )
            }
            composable(
                PlaylistScreenRoute.AdminListFilmItem.route,
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