package com.example.kinopoisk.screen.more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.kinopoisk.screen.filmInfo.viewState.ImageViewState
import com.example.kinopoisk.screen.more.view.ImageView
import com.example.kinopoisk.screen.more.view.TabView
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.utils.launchWhenStarted
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun ImageMoreScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    filmId:Int
) {
    val context = LocalContext.current
    val filmInfoViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .filmInfoViewModel()

    val filmInfo = remember { mutableStateOf(FilmInfo()) }
    val statePager = rememberPagerState(pageCount = ImageViewState.values().size)
    val scope = rememberCoroutineScope()

    filmInfoViewModel.getFilmInfo(id = filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        Text(text = filmInfo.value.nameRu)
                    }, navigationIcon = {
                        IconButton(onClick = {navController.navigate(FilmScreenRoute.FilmInfo.base(filmId = filmId.toString()))}) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    }
                )
                TabView(
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
                                    imageViewState = ImageViewState.STILL
                                )
                            }
                            1->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.SHOOTING
                                )
                            }
                            2->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.POSTER
                                )
                            }
                            3->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.FAN_ART
                                )
                            }
                            4->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.PROMO
                                )
                            }
                            5->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.CONCEPT
                                )
                            }
                            6->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.WALLPAPER
                                )
                            }
                            7->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.COVER
                                )
                            }
                            8->{
                                ImageView(
                                    filmId = filmId.toString(),
                                    imageViewState = ImageViewState.SCREENSHOT
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}