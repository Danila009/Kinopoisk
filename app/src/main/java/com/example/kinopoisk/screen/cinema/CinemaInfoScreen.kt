package com.example.kinopoisk.screen.cinema

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenConstants.Route.MAIN_ROUTE
import com.example.kinopoisk.screen.cinema.view.PhoneView
import com.example.kinopoisk.screen.cinema.view.ReviewView
import com.example.kinopoisk.screen.cinema.view.ScheduleView
import com.example.kinopoisk.screen.cinema.view.WebView
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Constants
import com.example.kinopoisk.utils.launchWhenStarted
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@Composable
fun CinemaInfoScreen(
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    cinemaId:Int
) {
    val context = LocalContext.current
    val cinemaViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .cinemaViewModel()

    val cinema = remember { mutableStateOf(Cinema()) }
    val pagerStateImage = rememberPagerState(pageCount = cinema.value.photoItems.size)
    val token = context
        .getSharedPreferences(Constants.TOKEN_SHARED, Context.MODE_PRIVATE)
        .getString(Constants.TOKEN_SHARED, "")

    cinemaViewModel.getCinema(
        id = cinemaId
    )
    cinemaViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = { Text(text = cinema.value.title)},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(MAIN_ROUTE) }) {
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
                        HorizontalPager(
                            state = pagerStateImage,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            repeat(cinema.value.photoItems.size){ index ->
                                if (index == it){
                                    Image(
                                        painter = rememberImagePainter(
                                            data = cinema.value.photoItems[index].photo,
                                            builder = {
                                                crossfade(true)
                                            }
                                        ), contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp)
                                    )
                                }
                            }
                        }
                    }

                    item{
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LazyRow(content = {
                                item {
                                    if (cinema.value.has3D){
                                        Text(
                                            text = "3D",
                                            modifier = Modifier
                                                .padding(5.dp),
                                            color = secondaryBackground
                                        )
                                    }
                                    if (cinema.value.hasImax){
                                        Text(
                                            text = "IMAX",
                                            modifier = Modifier
                                                .padding(5.dp),
                                            color = secondaryBackground
                                        )
                                    }
                                    if (cinema.value.has4DX){
                                        Text(
                                            text = "4DX",
                                            modifier = Modifier
                                                .padding(5.dp),
                                            color = secondaryBackground
                                        )
                                    }
                                    Text(
                                        text = cinema.value.rating.toString(),
                                        modifier = Modifier
                                            .padding(5.dp),
                                        color = secondaryBackground
                                    )
                                }
                            })

                            WebView(
                                navController = navController,
                                cinema = cinema.value
                            )
                        }
                    }

                    item {
                        Text(
                            text = "Телефон:",
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground
                        )
                    }

                    items(cinema.value.phoneItems){item ->
                        PhoneView(
                            phoneItem = item,
                            context = context
                        )
                    }

                    item {
                        Text(
                            text = "График работы:",
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground
                        )
                    }

                    items(cinema.value.schedules){ item ->
                        ScheduleView(
                            schedule = item
                        )
                    }

                    item {
                        Column {
                            Text(
                                text = "Друная информация:",
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight.Bold,
                                color = secondaryBackground
                            )
                            Text(
                                text = cinema.value.description,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }

                    item {
                        Text(
                            text = "Отзыввы:",
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground
                        )
                        if (token!!.isNotEmpty()){
                            TextButton(
                                modifier = Modifier.padding(5.dp),
                                onClick = { navController.navigate(
                                    CinemaScreenRoute.AddReviewCinema.base(
                                        cinemaId = cinemaId
                                    )
                                ) }) {
                                Text(
                                    text = "Добавить отзыв ->",
                                    modifier = Modifier.padding(5.dp),
                                    color = secondaryBackground
                                )
                            }
                        }
                    }
                    items(cinema.value.reviews){ item ->
                        ReviewView(
                            review = item
                        )
                    }
                })
            }
        }
    )
}