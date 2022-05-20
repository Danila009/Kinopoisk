package com.example.feature_film_images.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.state.ImageState
import com.example.feature_film_images.view.ImageView
import com.example.feature_film_images.view.TabImageView
import com.example.feature_film_images.viewModel.FilmImagesViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalPagerApi
@Composable
fun FilmImagesScreen(
    navController: NavController,
    filmImagesViewModel:FilmImagesViewModel,
    filmId:Int
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val filmInfo = remember { mutableStateOf(FilmInfo()) }
    val statePager = rememberPagerState(pageCount = ImageState.values().size)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit, block = {
        filmImagesViewModel.getFilmInfo(id = filmId)
    })

    filmImagesViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        Text(text = filmInfo.value.nameRu)
                    }, navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    }
                )
                TabImageView(
                    tabIndex = statePager.currentPage,
                    onClick = {
                        scope.launch {
                            statePager.animateScrollToPage(page = it.ordinal)
                        }
                    }
                )
            }
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                HorizontalPager(state = statePager) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        when(it){
                            0 ->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.STILL,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            1->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.SHOOTING,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            2->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.POSTER,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            3->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.FAN_ART,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            4->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.PROMO,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            5->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.CONCEPT,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            6->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.WALLPAPER,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            7->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.COVER,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                            8->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageState = ImageState.SCREENSHOT,
                                    filmImagesViewModel = filmImagesViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}