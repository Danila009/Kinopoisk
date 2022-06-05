package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.CHARACTERS
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.FILM_ID_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.VIDEO_STATE
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.WEB_URL_ARGUMENT
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.YOU_TUBE_URL
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Argument.YOU_TUBE_TITLE
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenConstants.Route.FILM_INFO_ROUTE
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.feature_film_info.screen.FilmInfoScreen
import com.example.feature_serial_info.screen.SerialInfoScreen
import com.example.feature_video_player.screen.VideoPlayerScreen
import com.example.feature_web.screen.WebScreen
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmMoreNavGraph.filmMoreNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.reviewFilmNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalSerializationApi
@ExperimentalPagerApi
fun NavGraphBuilder.filmInfoNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent
) {
    navigation(
        startDestination = FilmScreenRoute.FilmInfo.route,
        route = FILM_INFO_ROUTE,
        builder = {
            reviewFilmNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            filmMoreNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope,
                appComponent = appComponent
            )
            composable(
                FilmScreenRoute.FilmInfo.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        type = NavType.IntType
                    }
                )
            ){
                FilmInfoScreen(
                    navController = navController,
                    filmId = it.arguments?.getInt(FILM_ID_ARGUMENT)!!,
                    filmInfoViewModel = appComponent.filmInfoViewModel()
                )
            }
            composable(
                FilmScreenRoute.SerialInfoSeason.route,
                arguments = listOf(
                    navArgument(FILM_ID_ARGUMENT){
                        type = NavType.StringType
                    },
                    navArgument(CHARACTERS){
                        type = NavType.BoolType
                    }
                )
            ) {
                SerialInfoScreen(
                    navController = navController,
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toInt(),
                    serialInfoViewModel = appComponent.serialInfoViewModel(),
                    characters = it.arguments!!.getBoolean(CHARACTERS)
                )
            }
            composable(
                route = FilmScreenRoute.WebScreen.route,
                arguments = listOf(
                    navArgument(WEB_URL_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ){
                WebScreen(
                    navController = navController,
                    webUrl = it.arguments?.getString(WEB_URL_ARGUMENT).toString(),
                )
            }
            composable(
                route = FilmScreenRoute.VideoPlayer.route,
                arguments = listOf(
                    navArgument(VIDEO_STATE){
                        type = NavType.StringType
                    }, navArgument(YOU_TUBE_URL){
                        type = NavType.StringType
                        nullable = true
                    }, navArgument(YOU_TUBE_TITLE){
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ){
                VideoPlayerScreen(
                    videoPlayerState = enumValueOf(
                        it.arguments!!.getString(VIDEO_STATE, "")
                    ),
                    youtubeUrl = it.arguments!!.getString(YOU_TUBE_URL, ""),
                    youtubeTitle = it.arguments!!.getString(YOU_TUBE_TITLE, "")
                )
            }
        }
    )
}