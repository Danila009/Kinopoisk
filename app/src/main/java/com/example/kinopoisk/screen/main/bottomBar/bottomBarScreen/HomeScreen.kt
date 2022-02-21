package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.R
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.premiere.Premiere
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmTop.viewState.NameTopViewState
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import com.example.kinopoisk.utils.viewState.ViewStatePremiere
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController:NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    val checkNavMap = remember { mutableStateOf(false) }
    val premiere = remember { mutableStateOf(Premiere()) }
    val shop = remember { mutableStateOf(listOf<Shop>()) }
    val cinema = remember { mutableStateOf(listOf<Cinema>()) }

    val release = mainViewModel.getRelease(
        year = Converters().getDatePremiere(ViewStatePremiere.YEAR).toInt(),
        month = Converters().getDatePremiere(ViewStatePremiere.MONTH)
    ).collectAsLazyPagingItems()

    mainViewModel.getPremiere(
        year = Converters().getDatePremiere(ViewStatePremiere.YEAR).toInt(),
        month = Converters().getDatePremiere(ViewStatePremiere.MONTH)
    )
    mainViewModel.responsePremiere.onEach {
        premiere.value = it
    }.launchWhenStarted(lifecycleScope)

    mainViewModel.getShop()
    mainViewModel.responseShop.onEach {
        shop.value = it
    }.launchWhenStarted(lifecycleScope)

    mainViewModel.getCinema()
    mainViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = primaryBackground
    ) {
        LazyColumn(content = {
            item {
                premiere.value.total?.let {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Премьеры в кино:",
                            modifier = Modifier.padding(5.dp),
                            color = secondaryBackground,
                            fontWeight = FontWeight.Bold
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
                        items(premiere.value.items) { item ->
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
                                Text(
                                    text = Converters().getTime(item.premiereRu),
                                    modifier = Modifier.padding(
                                        top = 5.dp,
                                        start = 22.dp,
                                        bottom = 5.dp
                                    )
                                )
                            }
                        }
                    })
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Цифровые релизы:",
                        modifier = Modifier.padding(5.dp),
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
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
                    items(release) { item ->
                        Column(
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    Screen.FilmInfo.base(
                                        filmId = item?.filmId.toString()
                                    )
                                )
                            }
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
                                    .height(180.dp)
                                    .width(140.dp)
                            )
                            Text(
                                text = Converters().getTime(item!!.releaseDate),
                                modifier = Modifier.padding(
                                    top = 5.dp,
                                    start = 22.dp,
                                    bottom = 5.dp
                                )
                            )
                        }
                    }
                })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Shop:",
                        modifier = Modifier.padding(5.dp),
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
                    )

                    TextButton(
                        onClick = { navController.navigate(Screen.Shop.route)},
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Все ->",
                            color = secondaryBackground
                        )
                    }
                }

                LazyRow(content = {
                    items(shop.value){ item ->
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
                                    data = item.posterUrlPreview,
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
                            Text(
                                text = "${item.price} P",
                                modifier = Modifier.padding(
                                    top = 5.dp,
                                    start = 22.dp,
                                    bottom = 5.dp
                                )
                            )
                        }
                    }
                })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Cinema:",
                        modifier = Modifier.padding(5.dp),
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
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
                    itemsIndexed(cinema.value){ index, item ->
                        if (index < 5){
                            if (checkNavMap.value){
                                LaunchedEffect(key1 = Unit, block ={
                                    navController.navigate(
                                        Screen.CinemaInfo.base(
                                            cinemaId = item.id
                                        )
                                    )
                                })
                            }
                            Column(
                                modifier = Modifier
                                    .width(350.dp)
                                    .height(250.dp)
                            ) {
                                GoogleMap(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(300.dp)
                                        .height(200.dp)
                                        .clip(
                                            AbsoluteRoundedCornerShape(15.dp)
                                        ),
                                    cameraPositionState = CameraPositionState(
                                        CameraPosition(
                                            LatLng(
                                                item.mapOne,
                                                item.mapTwo
                                            ), 80f,1f,1f
                                        )
                                    ),
                                    content = {
                                        Marker(position = LatLng(
                                            item.mapOne,
                                            item.mapTwo
                                        ), title = "${item.title} ${item.adress}",
                                            onInfoWindowClick = {
                                                checkNavMap.value = true
                                        })
                                    }
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            checkNavMap.value = true
                                        },
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = item.title,
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                    Text(
                                        text = item.rating.toString(),
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                }
                            }
                        }
                    }
                })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Топы:",
                        modifier = Modifier.padding(5.dp),
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
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
                    items(4) {
                        when(it){
                            1->{
                                Image(
                                    painter = painterResource(id = R.drawable.iamgetopc),
                                    contentDescription = null,
                                    Modifier
                                        .size(150.dp)
                                        .padding(5.dp)
                                        .clickable {
                                            navController.navigate(
                                                Screen.FilmTop.base(
                                                    Converters().encodeToString(NameTopViewState.TOP_100_POPULAR_FILMS)
                                                )
                                            )
                                        }
                                )
                            }
                            2->{
                                Image(
                                    painter = painterResource(id = R.drawable.toplmagea),
                                    contentDescription = null,
                                    Modifier
                                        .size(150.dp)
                                        .padding(5.dp)
                                        .clickable {
                                            navController.navigate(
                                                Screen.FilmTop.base(
                                                    Converters().encodeToString(NameTopViewState.TOP_250_BEST_FILMS)
                                                )
                                            )
                                        }
                                )
                            }
                            3->{
                                Image(
                                    painter = painterResource(id = R.drawable.orig),
                                    contentDescription = null,
                                    Modifier
                                        .size(150.dp)
                                        .padding(5.dp)
                                        .clickable {
                                            navController.navigate(
                                                Screen.FilmTop.base(
                                                    Converters().encodeToString(NameTopViewState.TOP_AWAIT_FILMS)
                                                )
                                            )
                                        }
                                )
                            }
                        }
                    }
                })

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                )
            }
        })
    }
}