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
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.bottomBar.BottomBar
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.FilmsScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.HomeScreen
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController
) {
    val idBar = remember { mutableStateOf("Home") }

    Scaffold(
        topBar = {
            if (idBar.value == "Films"){
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {},
                    actions = {
                        IconButton(onClick = { navController.navigate(Screen.Sorting.route) }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = secondaryBackground
                            )
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
                    "Home" -> HomeScreen()
                    "Films" -> FilmsScreen()
                }
            }
        }
    )
}