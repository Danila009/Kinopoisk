package com.example.kinopoisk.navigation.navGraph.host

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kinopoisk.navigation.MAIN_ROUTE
import com.example.kinopoisk.navigation.ROUTE
import com.example.kinopoisk.navigation.navGraph.mainNavGraph

@ExperimentalFoundationApi
@Composable
fun Host(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = MAIN_ROUTE,
        route = ROUTE,
        builder = {
            mainNavGraph(
                navController = navHostController
            )
        }
    )
}