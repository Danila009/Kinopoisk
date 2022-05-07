package com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.cinemaNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.filmInfoNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.playlistNavGraph.filmTopNavGraph
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants.MainScreenConstants.Route.MAIN_ROUTE
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants.MainScreenRoute
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.sortingFilmNavGraph.sortingFilmNavGraph
import com.example.kinopoisk.navigation.navGraph.shopNavGraph.shopNavGraph
import com.example.kinopoisk.navigation.navGraph.staffInfoNavGraph.staffInfoNavGraph
import com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph.loginNavGraph
import com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph.settingNavGraph
import com.example.kinopoisk.screen.main.MainScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent
) {
    navigation(
        startDestination = MainScreenRoute.MainRoute.Main.route,
        route = MAIN_ROUTE,
        builder = {
            sortingFilmNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope,
                appComponent = appComponent
            )
            cinemaNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            settingNavGraph(
                navController = navController
            )
            filmInfoNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope,
            )
            filmTopNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            staffInfoNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            loginNavGraph(
                navController = navController,
                appComponent = appComponent
            )
            shopNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope
            )
            composable(MainScreenRoute.MainRoute.Main.route){
                MainScreen(
                    navController = navController,
                    appComponent = appComponent
                )
            }
        }
    )
}