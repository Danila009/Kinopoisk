package com.example.kinopoisk.screen.filmTop.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_utils.common.encodeToString
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun FilmListItemAddScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val filmTopViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .filmTopViewModel()

    val filmAddList = remember { mutableListOf<FilmItem>() }
    val films = filmTopViewModel.getFilm().collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "film list add item")
                }, navigationIcon = {
                    IconButton(onClick = {
                            navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    IconButton(onClick = {
                        navController.navigate(
                            PlaylistScreenRoute.FilmListAdd.base(
                                filmList = encodeToString(filmAddList)
                            ))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                LazyColumn(content = {
                    items(films){ item ->

                        val checkFilm = remember { mutableStateOf(false) }

                        LaunchedEffect(key1 = checkFilm.value, block = {
                            if (checkFilm.value){
                                filmAddList.add(item!!)
                            }else{
                                filmAddList.remove(item)
                            }
                        })

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        FilmScreenRoute.FilmInfo.base(
                                            item?.kinopoiskId.toString()
                                        )
                                    )
                                },
                            shape = AbsoluteRoundedCornerShape(15.dp),
                            backgroundColor = primaryBackground,
                            elevation = 8.dp
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = item?.posterUrl,
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(100.dp)
                                        .clip(AbsoluteRoundedCornerShape(10.dp))
                                )
                                Column {
                                    Text(
                                        text = item?.nameRu.toString(),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Checkbox(
                                        checked = checkFilm.value,
                                        onCheckedChange = { checkFilm.value = it },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = secondaryBackground
                                        )
                                    )
                                }
                            }
                        }
                    }

                    item {
                        if (
                            films.loadState.refresh is LoadState.Loading
                            || films.loadState.append is LoadState.Loading
                        ){
                            FilmListShimmer()
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                })
            }
        }
    )
}