package com.example.kinopoisk.navigation.navGraph.comicsNavGrah

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.comicsNavGraph.COMICS_ROUTE
import com.example.core_utils.navigation.comicsNavGraph.COMICS_STATE_ARGUMENT
import com.example.core_utils.navigation.comicsNavGraph.ComicsScreenRoute
import com.example.feature_comics.screen.ComicsScreen
import com.example.feature_comics_info.navigation.comicInfoNavigation
import com.example.kinopoisk.di.AppComponent
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
fun NavGraphBuilder.comicsNavGraph(
    navController: NavController,
    appComponent: AppComponent
) {
    navigation(
        startDestination = ComicsScreenRoute.ComicsScreen.route,
        route = COMICS_ROUTE,
        builder = {
            composable(
                route = ComicsScreenRoute.ComicsScreen.route,
                arguments = listOf(
                    navArgument(
                        name = COMICS_STATE_ARGUMENT,
                        builder = {
                            type = NavType.StringType
                        }
                    )
                ),
                content = {
                    ComicsScreen(
                        navController = navController,
                        comicsViewModel = appComponent.comicsViewModel(),
                        comicsState = enumValueOf(
                            name = it.arguments?.getString(COMICS_STATE_ARGUMENT)!!
                        )
                    )
                }
            )

            comicInfoNavigation(
                viewModel = appComponent.comicInfoViewModel(),
                navController = navController
            )
        }
    )
}