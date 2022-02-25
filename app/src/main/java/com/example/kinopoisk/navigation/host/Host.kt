package com.example.kinopoisk.navigation.host

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kinopoisk.navigation.MAIN_ROUTE
import com.example.kinopoisk.navigation.ROUTE
import com.example.kinopoisk.navigation.navGraph.*
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.cinemaNavGraph
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph
import com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph
import com.example.kinopoisk.navigation.navGraph.userNavGraph.settingNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun Host(
    navHostController: NavHostController,
    lifecycleScope: LifecycleCoroutineScope,
) {
    NavHost(
        navController = navHostController,
        startDestination = MAIN_ROUTE,
        route = ROUTE,
        builder = {
            mainNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope,
            )
            filmInfoNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope,
            )
            filmTopNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope
            )
            staffInfoNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope
            )
            moreNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope
            )
            reviewNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope
            )
            loginNavGraph(
                navController = navHostController
            )
            shopNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope
            )
            cinemaNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope
            )
            settingNavGraph(
                navController = navHostController
            )
        }
    )
}