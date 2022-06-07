package com.example.feature_comics_info.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core_utils.navigation.comicsNavGraph.COMICS_STATE_ARGUMENT
import com.example.core_utils.navigation.comicsNavGraph.COMIC_ID_ARGUMENT
import com.example.core_utils.navigation.comicsNavGraph.ComicsScreenRoute
import com.example.feature_comics_info.screen.ComicInfoScreen
import com.example.feature_comics_info.viewModel.ComicInfoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.comicInfoNavigation(
    viewModel:ComicInfoViewModel,
    navController: NavController
) {
    composable(
        route = ComicsScreenRoute.ComicInfoScreen.route,
        arguments = listOf(
            navArgument(COMIC_ID_ARGUMENT){
                type = NavType.IntType
            },
            navArgument(COMICS_STATE_ARGUMENT){
                type = NavType.StringType
            }
        )
    ){
        ComicInfoScreen(
            viewModel = viewModel,
            navController = navController,
            comicsState = enumValueOf(it.arguments?.getString(COMICS_STATE_ARGUMENT)!!),
            comicId = it.arguments!!.getInt(COMIC_ID_ARGUMENT, 0)
        )
    }
}