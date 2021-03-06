package com.example.kinopoisk.screen.more

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.common.replaceRange
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.filmNavGraph.reviewFilmNavGraph.ReviewFilmScreenRoute
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun ReviewMoreScreen(
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    filmId:Int
) {
    val context = LocalContext.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val filmInfoViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .filmInfoViewModel()

    val filmInfo = remember { mutableStateOf(FilmInfo()) }

    filmInfoViewModel.getFilmInfo(id = filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    val review = filmInfoViewModel.getReview(
        id = filmId
    ).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 8.dp,
                backgroundColor = primaryBackground,
                title = {
                    Text(text = filmInfo.value.nameRu)
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(FilmScreenRoute.FilmInfo.base(filmId = filmId)) }) {
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
                                        ReviewFilmScreenRoute.ReviewDetail.base(
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
                                    text = replaceRange(item?.reviewDescription.toString(), 500),
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