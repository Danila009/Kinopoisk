package com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.feature_films.screen.FilmsScreen
import com.example.feature_home.screen.HomeScreen
import com.example.feature_persons.screen.SearchScreen
import com.example.feature_profile.screen.ProfileScreen
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.cinemaNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.filmInfoNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.playlistNavGraph.filmTopNavGraph
import com.example.core_utils.navigation.mainNavGraph.MainScreenConstants.Route.MAIN_ROUTE
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.kinopoisk.navigation.navGraph.comicsNavGrah.comicsNavGraph
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.sortingFilmNavGraph.sortingFilmNavGraph
import com.example.kinopoisk.navigation.navGraph.shopNavGraph.shopNavGraph
import com.example.kinopoisk.navigation.navGraph.staffInfoNavGraph.staffInfoNavGraph
import com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph.loginNavGraph
import com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph.settingNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalMaterialApi
@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent
) {
    navigation(
        startDestination = MainScreenRoute.MainRoute.Home.route,
        route = MAIN_ROUTE,
        builder = {
            sortingFilmNavGraph(
                navController = navController,
                appComponent = appComponent
            )
            cinemaNavGraph(
                navController = navController,
                appComponent = appComponent
            )
            settingNavGraph(
                navController = navController,
                appComponent = appComponent
            )
            filmInfoNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope,
                appComponent = appComponent
            )
            filmTopNavGraph(
                navController = navController,
                lifecycleScope = lifecycleScope,
                appComponent = appComponent
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
            comicsNavGraph(
                navController = navController,
                appComponent = appComponent
            )
            composable(MainScreenRoute.MainRoute.Home.route){
                HomeScreen(
                    navController = navController,
                    homeViewModel = appComponent.homeViewModel()
                )
            }
            composable(MainScreenRoute.MainRoute.Films.route){
                FilmsScreen(
                    navController = navController,
                    filmsViewMode = appComponent.filmsViewModel()
                )
            }
            composable(MainScreenRoute.MainRoute.Search.route){
                SearchScreen(
                    navController = navController,
                    searchViewModel = appComponent.searchViewModel()
                )
            }
            composable(MainScreenRoute.MainRoute.Profile.route){
                ProfileScreen(
                    navController = navController,
                    profileViewModel = appComponent.profileViewModel()
                )
            }
        }
    )
}