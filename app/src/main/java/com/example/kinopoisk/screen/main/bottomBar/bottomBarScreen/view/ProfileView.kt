package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.launchWhenStarted
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileView(
    mainViewModel: MainViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController:NavController
) {
    val userInfo = remember { mutableStateOf(UserInfo()) }

    mainViewModel.getUserInfo()
    mainViewModel.responseUserInfo.onEach {
        userInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    LazyColumn(content = {
        item {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                Column {
                    TopAppBar(
                        backgroundColor = primaryBackground,
                        elevation = 8.dp,
                        title = {
                            Text(
                                text = userInfo.value.username,
                                color = secondaryBackground
                            )
                        }, actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    tint = secondaryBackground
                                )
                            }
                        }
                    )

                    if (userInfo.value.favoritFilm.isNotEmpty()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Favorite film:",
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight.Bold,
                                color = secondaryBackground
                            )

                            TextButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Все ->",
                                    color = secondaryBackground
                                )
                            }
                        }
                        LazyRow(content = {
                            items(userInfo.value.favoritFilm) { item ->
                                Column(
                                    modifier = Modifier.clickable {
                                        navController.navigate(
                                            Screen.FilmInfo.base(
                                                filmId = item.kinopoiskId.toString()
                                            )
                                        )
                                    }
                                ) {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = item.posterUrl,
                                            builder = {
                                                crossfade(true)
                                            }
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .height(180.dp)
                                            .width(140.dp)
                                    )
                                }
                            }
                        })
                    }
                }
            }
        }
    })
}