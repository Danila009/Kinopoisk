package com.example.kinopoisk.navigation.host

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants.MainScreenConstants.Route.MAIN_ROUTE
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.mainNavGraph
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
        route = "route",
        builder = {
            mainNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope,
            )
        }
    )
}