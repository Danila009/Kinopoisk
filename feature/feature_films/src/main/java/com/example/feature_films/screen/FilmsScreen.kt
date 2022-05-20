package com.example.feature_films.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.SearchView
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenRoute
import com.example.feature_films.viewModel.FilmsViewModel

@ExperimentalFoundationApi
@Composable
fun FilmsScreen(
    navController: NavController,
    filmsViewMode:FilmsViewModel,
    order:String = "RATING",
    type:String = "ALL",
    ratingFrom:Int = 0,
    ratingTo:Int = 10,
    yearFrom:Int = 1000,
    yearTo:Int = 3000,
    genres:List<Int> = listOf(),
    countries:List<Int> = listOf()
) {

    val check = remember { mutableStateOf(false) }

    val search = remember { mutableStateOf("") }

    val filmList = filmsViewMode.getFilm(
        genres = genres,
        countries = countries,
        order = order,
        type = type,
        ratingFrom = ratingFrom,
        ratingTo = ratingTo,
        yearFrom = yearFrom,
        yearTo = yearTo,
        keyword = search.value
    ).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    SearchView(
                        search = {
                            search.value = it
                        }
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(SortingScreenRoute.SortingFilm.base()) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = primaryBackground
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
                items(filmList){ item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .padding(horizontal = 9.dp, vertical = 5.dp)
                            .clickable {
                                navController.navigate(
                                    FilmScreenRoute.FilmInfo.base(
                                        item?.kinopoiskId!!
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
                            SubcomposeAsyncImage(
                                model = item?.posterUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clip(AbsoluteRoundedCornerShape(10.dp))
                                    .width(100.dp)
                            ) {
                                val state = painter.state
                                if (
                                    state is AsyncImagePainter.State.Loading ||
                                    state is AsyncImagePainter.State.Error
                                ) {
                                    ImageShimmer(
                                        imageHeight = 170.dp,
                                        imageWidth = 100.dp
                                    )
                                } else {
                                    SubcomposeAsyncImageContent()
                                }
                            }

                            Text(
                                text = item?.nameRu.toString(),
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }


                if (check.value){
                    item {
                        FilmListShimmer()
                    }
                }

                filmList.apply {
                    when{
                        loadState.refresh is LoadState.Loading -> check.value = true

                        loadState.append is LoadState.Loading -> check.value = true

                    }
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    )
                }
            })
        }
    }
}