package com.example.kinopoisk.navigation.host

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.kinopoisk.di.AppComponent
import com.example.core_utils.navigation.mainNavGraph.MainScreenConstants.Route.MAIN_ROUTE
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.mainNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalMaterialApi
@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun BaseNavHost(
    navHostController: NavHostController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent,
    activity: Activity
) {

    var idBottomBar by remember { mutableStateOf(BottomBar.Home) }

    val route = navHostController.currentBackStackEntryAsState().value?.destination?.route

    val systemUiController = rememberSystemUiController()

    if (route != FilmScreenRoute.VideoPlayer.route){
        systemUiController.isStatusBarVisible = true
        systemUiController.isNavigationBarVisible = true
        systemUiController.isSystemBarsVisible = true
        systemUiController.isNavigationBarContrastEnforced = true
        systemUiController.navigationBarDarkContentEnabled = true
        systemUiController.statusBarDarkContentEnabled = true
        systemUiController.systemBarsDarkContentEnabled = true
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }else {
        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
        systemUiController.isSystemBarsVisible = false
        systemUiController.isNavigationBarContrastEnforced = false
        systemUiController.navigationBarDarkContentEnabled = false
        systemUiController.statusBarDarkContentEnabled = false
        systemUiController.systemBarsDarkContentEnabled = false
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    Scaffold(
        bottomBar = {
            if (route != FilmScreenRoute.VideoPlayer.route){
                BottomNavigation(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp
                ) {
                    BottomBar.values().forEach { item ->
                        BottomNavigationItem(
                            selected = idBottomBar == item,
                            onClick = {
                                idBottomBar = item

                                when(idBottomBar){
                                    BottomBar.Home -> navHostController.navigate(
                                        route = MainScreenRoute.MainRoute.Home.route
                                    )
                                    BottomBar.Films -> navHostController.navigate(
                                        route = MainScreenRoute.MainRoute.Films.route
                                    )
                                    BottomBar.Search -> {
                                        navHostController.navigate(
                                            route = MainScreenRoute.MainRoute.Search.route
                                        )
                                    }
                                    BottomBar.Profile -> navHostController.navigate(
                                        route = MainScreenRoute.MainRoute.Profile.route
                                    )
                                }
                            },
                            label = { Text(text = item.name) },
                            icon = { Icon(imageVector = item.icon, contentDescription = null) },
                            selectedContentColor = secondaryBackground,
                            unselectedContentColor = Color.White,
                        )
                    }
                }
            }
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                NavHost(
                    navController = navHostController,
                    startDestination = MAIN_ROUTE,
                    route = "route",
                    builder = {
                        mainNavGraph(
                            navController = navHostController,
                            lifecycleScope = lifecycleScope,
                            appComponent = appComponent
                        )
                    }
                )
            }
        }
    )
}