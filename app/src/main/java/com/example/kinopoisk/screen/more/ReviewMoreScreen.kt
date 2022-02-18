package com.example.kinopoisk.screen.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmInfo.FilmInfoViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@Composable
fun ReviewMoreScreen(
    filmInfoViewModel: FilmInfoViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    filmId:Int
) {
    val filmInfo = remember { mutableStateOf(FilmInfo()) }

    filmInfoViewModel.getFilmInfo(id = filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    val review = filmInfoViewModel.getReview(
        id = filmId
    ).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 8.dp,
                backgroundColor = primaryBackground,
                title = {
                    Text(text = filmInfo.value.nameRu.toString())
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.FilmInfo.base(filmId = filmId.toString())) }) {
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
                    items(review) { item ->
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.ReviewDetail.base(
                                            reviewId = item?.reviewId.toString(),
                                            filmId = filmId.toString()
                                        )
                                    )
                                },
                            shape = AbsoluteRoundedCornerShape(20.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = item?.reviewAutor.toString(),
                                        modifier = Modifier.padding(5.dp),
                                        color = secondaryBackground
                                    )
                                    Text(
                                        text = "+ ${item?.userPositiveRating.toString()}",
                                        color = Color.Green,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "- ${item?.userNegativeRating.toString()}",
                                        color = Color.Red,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                                Text(
                                    text = item?.reviewTitle.toString(),
                                    modifier = Modifier.padding(5.dp)
                                )
                                Text(
                                    text = Converters().replaceRange(item?.reviewDescription.toString(), 500),
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