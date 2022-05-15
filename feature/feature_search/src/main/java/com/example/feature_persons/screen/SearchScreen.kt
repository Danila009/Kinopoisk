package com.example.feature_persons.screen

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.movie.history.HistorySearchItem
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.SearchView
import com.example.core_utils.common.getDate
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.state.ComicsState
import com.example.feature_persons.tab.SortingTab
import com.example.feature_persons.view.*
import com.example.feature_persons.view.CinemaView
import com.example.feature_persons.view.HistoryMovieView
import com.example.feature_persons.view.HistorySearchView
import com.example.feature_persons.view.MovieView
import com.example.feature_persons.view.searchResult.movieResultView
import com.example.feature_persons.viewModel.SearchViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalMaterialApi
@ExperimentalSerializationApi
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val search = remember { mutableStateOf("") }

    val movie = searchViewModel.getFilm(
        keyword = search.value
    ).collectAsLazyPagingItems()

    val comicsMarvel = searchViewModel.getComicsMarvel(
        search = search.value
    ).collectAsLazyPagingItems()

    var historySearch:Response<HistorySearch> by remember { mutableStateOf(Response.Loading()) }
    var historyMovie:Response<HistoryMovie> by remember { mutableStateOf(Response.Loading()) }
    var cinema:Response<List<Cinema>> by remember { mutableStateOf(Response.Loading()) }

    var sortingSelectedTabIndex by remember { mutableStateOf(0) }

    val expandedStateFilm = remember { mutableStateOf(false) }
    val rotationStateFilm by animateFloatAsState(
        targetValue = if (expandedStateFilm.value) 180f else 0f
    )

    var expandedStateCinema by remember { mutableStateOf(false) }
    val rotationStateCinema by animateFloatAsState(
        targetValue = if (expandedStateCinema) 180f else 0f
    )

    var movieTotal by remember { mutableStateOf(0) }

    searchViewModel.responseHistorySearch.onEach {
        historySearch = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseHistoryMovie.onEach {
        historyMovie = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseCinema.onEach {
        cinema = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseMovieTotal.onEach {
        movieTotal = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    LaunchedEffect(key1 = search.value, block = {

        searchViewModel.getCinema(
            search = search.value
        )

        if (search.value.isNotEmpty()){
            searchViewModel.postHistorySearch(
                historySearchItem = HistorySearchItem(
                    id = 0,
                    title = search.value,
                    date = getDate()
                )
            )
        }
    })

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        SearchView(
                            search = search
                        )
                    }
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(primaryBackground)
                )

                TabRow(
                    selectedTabIndex = sortingSelectedTabIndex,
                    backgroundColor = primaryBackground,
                    contentColor = secondaryBackground
                ) {
                    SortingTab.values().forEach { item ->
                        Tab(
                            selected = sortingSelectedTabIndex == item.ordinal,
                            onClick = {
                                sortingSelectedTabIndex = item.ordinal
                            },
                            text = { Text(text = item.title) }
                        )
                    }
                }
            }
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                LazyColumn(content = {
                    when(sortingSelectedTabIndex){
                        0 -> {
                            if (search.value.isEmpty()){
                                item {
                                    HistorySearchView(
                                        historySearch = historySearch,
                                        search = {
                                            search.value = it
                                        }
                                    )

                                    HistoryMovieView(
                                        navController = navController,
                                        historyMovie = historyMovie
                                    )

                                    MovieView(
                                        navController = navController,
                                        movie = movie
                                    )

                                    CinemaView(
                                        navController = navController,
                                        cinema = cinema
                                    )

                                    ComicsView(
                                        navController = navController,
                                        comicsState = ComicsState.MARVEL,
                                        comicsMarvel = comicsMarvel
                                    )
                                }
                            } else {

                                movieResultView(
                                    movie = movie,
                                    expandedStateFilm = expandedStateFilm,
                                    rotationStateFilm = rotationStateFilm,
                                    movieTotal = movieTotal
                                )

                                item {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .animateContentSize(
                                                animationSpec = tween(
                                                    durationMillis = 300,
                                                    easing = LinearOutSlowInEasing
                                                )
                                            ),
                                        backgroundColor = primaryBackground,
                                        onClick = {
                                            expandedStateCinema = !expandedStateCinema
                                        }
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ){
                                            Text(
                                                text = "Cinema ${cinema.data?.size}",
                                                modifier = Modifier.padding(5.dp),
                                                color = secondaryBackground
                                            )

                                            IconButton(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .alpha(ContentAlpha.medium)
                                                    .rotate(rotationStateCinema),
                                                onClick = {
                                                    expandedStateCinema = !expandedStateCinema
                                                }) {
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = null
                                                )
                                            }

                                            if (expandedStateCinema){
                                                cinema.data?.let { items ->
                                                    if(items.isNotEmpty()){
                                                        items.forEach { item ->
                                                            Column {
                                                                Row {
                                                                    SubcomposeAsyncImage(
                                                                        model = item.icon,
                                                                        contentDescription = null,
                                                                        modifier = Modifier
                                                                            .padding(5.dp)
                                                                            .height(100.dp)
                                                                            .width(150.dp)
                                                                    ) {
                                                                        val state = painter.state
                                                                        if (
                                                                            state is AsyncImagePainter.State.Loading ||
                                                                            state is AsyncImagePainter.State.Error
                                                                        ) {
                                                                            ImageShimmer(
                                                                                imageHeight = 100.dp,
                                                                                imageWidth = 150.dp
                                                                            )
                                                                        } else {
                                                                            SubcomposeAsyncImageContent()
                                                                        }
                                                                    }

                                                                    Text(
                                                                        text = item.title,
                                                                        modifier = Modifier.padding(5.dp)
                                                                    )
                                                                }
                                                                Divider()
                                                            }
                                                        }
                                                    }else {
                                                        FilmListShimmer()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        1 -> item { SortingView() }
                    }

                    item {
                        Spacer(
                            modifier = Modifier.height(60.dp)
                        )
                    }
                })
            }
        }
    )
}