package com.example.kinopoisk.screen.cinema

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.R
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.navigation.MAIN_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.cinema.viewModel.CinemaViewModel
import com.example.kinopoisk.screen.main.key.WebScreenKey
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@Composable
fun CinemaInfoScreen(
    cinemaViewModel: CinemaViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    cinemaId:Int
) {
    val context = LocalContext.current
    val cinema = remember { mutableStateOf(Cinema()) }
    val pagerStateImage = rememberPagerState(pageCount = cinema.value.photoItems.size)

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

                            LazyRow(content = {
                                items(4){ item ->
                                    when(item){
                                        0->{
                                            IconButton(onClick = {
                                                navController.navigate(
                                                    Screen.WebScreen.base(
                                                        keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                                        webUrl = cinema.value.website,
                                                        cinemaId = cinema.value.id.toString()
                                                    )
                                                )
                                            }, modifier = Modifier
                                                .padding(5.dp)) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.web),
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                        1->{
                                            IconButton(onClick = {
                                                navController.navigate(
                                                    Screen.WebScreen.base(
                                                        keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                                        webUrl = cinema.value.webVk,
                                                        cinemaId = cinema.value.id.toString()
                                                    )
                                                )
                                            }, modifier = Modifier
                                                .padding(5.dp)) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.vk),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(50.dp)
                                                )
                                            }
                                        }
                                        2->{
                                            IconButton(onClick = {
                                                navController.navigate(
                                                    Screen.WebScreen.base(
                                                        keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                                        webUrl = cinema.value.webInstagram,
                                                        cinemaId = cinema.value.id.toString()
                                                    )
                                                )
                                            }, modifier = Modifier
                                                .padding(5.dp)) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.instagram),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(50.dp)
                                                )
                                            }
                                        }
                                        3->{IconButton(onClick = {
                                            navController.navigate(
                                                Screen.WebScreen.base(
                                                    keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                                    webUrl = cinema.value.webFacebook,
                                                    cinemaId = cinema.value.id.toString()
                                                )
                                            )
                                        }, modifier = Modifier
                                            .padding(5.dp)) {
                                            Image(
                                                painter = painterResource(id = R.drawable.facebook),
                                                contentDescription = null,
                                                modifier = Modifier.size(50.dp)
                                            )
                                        }}
                                    }
                                }
                            })
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
                        TextButton(onClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:${item.phone}")
                            context.startActivity(intent)
                        }) {
                            Text(
                                text = item.phone,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }

                    item {
                        Text(
                            text = "График работы:",
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground
                        )
                    }

                    items(cinema.value.schedules){ item ->
                        Row(
                            modifier = Modifier
                                .width(220.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.week,
                                modifier = Modifier.padding(5.dp),
                                color = secondaryBackground
                            )
                            Text(
                                text = "${item.startDate} - ${item.endDate}",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
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
                        TextButton(
                            modifier = Modifier.padding(5.dp),
                            onClick = { navController.navigate(
                            Screen.AddReviewCinema.base(
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

                    items(cinema.value.reviews){ items ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(primaryBackground),
                            elevation = 8.dp,
                            shape = AbsoluteRoundedCornerShape(15.dp)
                        ) {
                            Column {
                                Text(
                                    text = items.userName,
                                    modifier = Modifier.padding(5.dp),
                                    color = secondaryBackground,
                                    fontWeight = FontWeight.Bold
                                )
                                Row {
                                    Text(
                                        text = items.title,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = items.rating.toString(),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                                Text(
                                    text = items.description,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Text(
                                    text = Converters().getTime(items.date),
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }
                })
            }
        }
    )
}