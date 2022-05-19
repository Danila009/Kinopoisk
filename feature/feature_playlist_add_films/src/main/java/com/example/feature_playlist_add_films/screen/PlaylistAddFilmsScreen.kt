package com.example.feature_playlist_add_films.screen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.model.movie.FilmItem
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.encodeToString
import com.example.core_utils.common.rating
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.feature_playlist_add_films.viewModel.PlaylistAddFilmsViewModel

@Composable
fun PlaylistAddFilmsScreen(
    navController: NavController,
    playlistAddFilmsViewModel: PlaylistAddFilmsViewModel
) {
    val filmAddList = remember { mutableListOf<FilmItem>() }
    val films = playlistAddFilmsViewModel.getFilm(

    ).collectAsLazyPagingItems()

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
                                Box {
                                    SubcomposeAsyncImage(
                                        model = item?.posterUrlPreview,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .height(180.dp)
                                            .width(140.dp)
                                    ) {
                                        val state = painter.state
                                        if (
                                            state is AsyncImagePainter.State.Loading ||
                                            state is AsyncImagePainter.State.Error
                                        ) {
                                            ImageShimmer(
                                                imageHeight = 180.dp,
                                                imageWidth = 140.dp
                                            )
                                        } else {
                                            SubcomposeAsyncImageContent()
                                        }
                                    }
                                    Column {
                                        Spacer(modifier = Modifier.height(150.dp))
                                        Row {
                                            Spacer(modifier = Modifier.width(100.dp))
                                            Card(
                                                shape = AbsoluteRoundedCornerShape(5.dp),
                                                backgroundColor = rating(item?.ratingKinopoisk ?: 0f)
                                            ) {
                                                Text(
                                                    text = item?.ratingKinopoisk.toString(),
                                                    modifier = Modifier.padding(5.dp),
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }

                                Row {
                                    Text(
                                        text = "${item?.nameRu}, ${item?.year}",
                                        modifier = Modifier.padding(5.dp),
                                        fontWeight = FontWeight.Bold
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