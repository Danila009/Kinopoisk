package com.example.kinopoisk.navigation.host

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kinopoisk.navigation.MAIN_ROUTE
import com.example.kinopoisk.navigation.ROUTE
import com.example.kinopoisk.navigation.navGraph.*
import com.example.kinopoisk.navigation.navGraph.userNavGraph.loginNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun Host(
    navHostController: NavHostController,
    lifecycleScope: LifecycleCoroutineScope,
    buttonNav: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = MAIN_ROUTE,
        route = ROUTE,
        builder = {
            mainNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope,
                buttonNav = buttonNav
            )
            filmInfoNavGraph(
                navController = navHostController,
                lifecycleScope = lifecycleScope,
                buttonNav = buttonNav
            )
            filmTopNavGraph(
                navController = navHostController,
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
                navController = navHostController
            )
        }
    )
}