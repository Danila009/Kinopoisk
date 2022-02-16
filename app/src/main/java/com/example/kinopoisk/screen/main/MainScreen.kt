package com.example.kinopoisk.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.bottomBar.BottomBar
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.FilmsScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.HomeScreen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.ProfileScreen
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController,
    lifecycleScope:LifecycleCoroutineScope
) {
    val idBar = remember { mutableStateOf("Home") }
    val search = remember { (mutableStateOf("")) }

    Scaffold(
        topBar = {
            if (idBar.value == "Films"){
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        TextField(
                            value = search.value,
                            onValueChange = {
                            search.value = it
                        }, placeholder = {
                            Text(text = "Search")
                            }, leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = secondaryBackground
                                )
                            }, keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ), shape = AbsoluteRoundedCornerShape(5.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = secondaryBackground,
                                backgroundColor = primaryBackground
                            ), trailingIcon = {
                                if (search.value.isNotEmpty()){
                                    IconButton(onClick = { search.value = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            tint = secondaryBackground
                                        )
                                    }
                                }
                            }
                        )
                    },
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
                    "Home" -> HomeScreen(
                        lifecycleScope = lifecycleScope,
                        navController = navController
                    )
                    "Films" -> FilmsScreen(
                        navController = navController,
                        keyword = search.value
                    )
                    "Profile" -> ProfileScreen(
                        navController = navController,
                        lifecycleScope = lifecycleScope
                    )
                }
            }
        }
    )
}