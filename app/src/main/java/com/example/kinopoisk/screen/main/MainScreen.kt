package com.example.kinopoisk.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_films.screen.FilmsScreen
import com.example.feature_home.screen.HomeScreen
import com.example.feature_persons.screen.PersonScreen
import com.example.feature_profile.screen.ProfileScreen
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.sortingFilmNavGraph.constants.SortingScreenRoute
import com.example.kinopoisk.screen.main.bottomBar.BottomBar
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.SearchView
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController,
    appComponent: AppComponent
) {
    val filmSearch = remember { mutableStateOf("") }
    val personSearch = remember { mutableStateOf("") }
    val idBar = remember { mutableStateOf("Home") }

    Scaffold(
        topBar = {
            if (idBar.value == BottomBar.Films.name || idBar.value == BottomBar.Persons.name){
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        SearchView(search = if (idBar.value == BottomBar.Films.name) filmSearch else personSearch)
                    },
                    actions = {
                        if (idBar.value == BottomBar.Films.name){
                            IconButton(onClick = { navController.navigate(SortingScreenRoute.SortingFilm.base()) }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null,
                                    tint = secondaryBackground
                                )
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor = primaryBackground, elevation = 8.dp) {
                BottomBar.values().forEach { item ->
                    BottomNavigationItem(
                        selected = idBar.value == item.name,
                        onClick = { idBar.value = item.name },
                        label = { Text(text = item.name)},
                        icon = { Icon(imageVector = item.icon, contentDescription = null)},
                        selectedContentColor = secondaryBackground,
                        unselectedContentColor = Color.White
                    )
                }
            }
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                when(idBar.value){
                    BottomBar.Home.name -> HomeScreen(
                        homeViewModel = appComponent.homeViewModel(),
                        navController = navController
                    )
                    BottomBar.Films.name -> FilmsScreen(
                        navController = navController,
                        keyword = filmSearch.value,
                        filmsViewMode = appComponent.filmsViewModel()
                    )
                    BottomBar.Persons.name -> PersonScreen(
                        navController = navController,
                        search = personSearch.value,
                        personsViewModel = appComponent.personViewModel()
                    )
                    BottomBar.Profile.name -> ProfileScreen(
                        navController = navController,
                        profileViewModel = appComponent.profileViewModel()
                    )
                }
            }
        }
    )
}