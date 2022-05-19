package com.example.feature_palylist_add.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.MovieItem
import com.example.core_network_domain.model.playlist.PlaylistAdd
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.BaseButtonView
import com.example.core_ui.view.BaseTextFieldView
import com.example.core_utils.common.getDate
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.common.rating
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.feature_palylist_add.viewModel.PlaylistAddViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalSerializationApi
@Composable
fun PlaylistAddScreen(
    playlistAddViewModel: PlaylistAddViewModel,
    navController: NavController,
    playlistFilms: List<MovieItem>? = null
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    var playlistResult:Response<Void?>? by remember { mutableStateOf(null) }

    playlistAddViewModel.responsePlaylistAddResult.onEach {
        playlistResult = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    LaunchedEffect(key1 = playlistResult, block = {
        playlistResult?.let {
            if (playlistResult is Response.Success){
                navController.navigate(MainScreenRoute.MainRoute.Home.route)
            }
        }
    })

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Playlist add")
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
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
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (playlistResult is Response.Loading){
                                LinearProgressIndicator(
                                    color = secondaryBackground
                                )
                            }else if (playlistResult is Response.Error){
                                Text(
                                    text = playlistResult?.message ?: "Error",
                                    modifier = Modifier.padding(5.dp),
                                    color = Color.Red
                                )
                            }

                            BaseTextFieldView(
                                label = "Title",
                                value = title
                            )

                            BaseTextFieldView(
                                label = "Description",
                                value = description
                            )

                            BaseButtonView(text = "Add movie in playlist") {
                                navController.navigate(PlaylistScreenRoute.FilmListItemAdd.route)
                            }

                            BaseButtonView(text = "Add playlist") {
                                playlistAddViewModel.postPlaylist(
                                    PlaylistAdd(
                                        title = title.value,
                                        description = description.value,
                                        date = getDate(),
                                        movie = playlistFilms ?: listOf()
                                    )
                                )
                            }
                        }
                    }

                    playlistFilms?.let {
                        items(it){ item ->
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box {
                                        SubcomposeAsyncImage(
                                            model = item.posterUrlPreview,
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
                                                    backgroundColor = rating(item.ratingKinopoisk ?: 0f)
                                                ) {
                                                    Text(
                                                        text = item.ratingKinopoisk.toString(),
                                                        modifier = Modifier.padding(5.dp),
                                                        color = Color.White,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    Text(
                                        text = item.nameRu ?: "",
                                        modifier = Modifier.padding(5.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Divider()
                            }
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