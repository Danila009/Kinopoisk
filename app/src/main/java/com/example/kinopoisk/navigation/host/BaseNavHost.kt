package com.example.kinopoisk.navigation.host

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
import com.example.kinopoisk.di.AppComponent
import com.example.core_utils.navigation.mainNavGraph.MainScreenConstants.Route.MAIN_ROUTE
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.mainNavGraph
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalMaterialApi
@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun BaseNavHost(
    navHostController: NavHostController,
    lifecycleScope: LifecycleCoroutineScope,
    appComponent: AppComponent
) {
    var idBottomBar by remember { mutableStateOf(BottomBar.Home) }

    Scaffold(
        bottomBar = {
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
                                BottomBar.Persons -> navHostController.navigate(
                                    route = MainScreenRoute.MainRoute.Person.route
                                )
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