package com.example.kinopoisk.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kinopoisk.navigation.BottomScreen
import com.example.kinopoisk.screen.main.bottomBar.BottomBar
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.FilmsScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.HomeScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.PersonScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.ProfileScreen
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController,
    lifecycleScope:LifecycleCoroutineScope,
    bottomNav: NavHostController
) {
    val bottomNavItems by bottomNav.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = primaryBackground, elevation = 8.dp) {
                BottomBar.values().forEach { item ->
                    BottomNavigationItem(
                        selected = bottomNavItems?.destination?.route == item.nav,
                        onClick = { bottomNav.navigate(item.nav) },
                        label = { Text(text = item.name)},
                        icon = { Icon(imageVector = item.icon, contentDescription = null)},
                        selectedContentColor = secondaryBackground,
                        unselectedContentColor = Color.White
                    )
                }
            }
        }, content = {
            NavHost(
                navController = bottomNav,
                startDestination = BottomScreen.Home.route,
                builder = {
                    composable(BottomScreen.Home.route){
                        HomeScreen(
                            lifecycleScope = lifecycleScope,
                            navController = navController
                        )
                    }
                    composable(BottomScreen.Films.route){
                        FilmsScreen(
                            navController = navController
                        )
                    }
                    composable(BottomScreen.Profile.route){
                        ProfileScreen(
                            navController = navController,
                            lifecycleScope = lifecycleScope
                        )
                    }
                    composable(BottomScreen.Person.route){
                        PersonScreen(
                            navController = navController
                        )
                    }
                }
            )
        }
    )
}