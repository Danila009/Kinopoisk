package com.example.feature_persons.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.movie.history.HistorySearchItem
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
import com.example.feature_persons.view.searchResult.*
import com.example.feature_persons.view.searchResult.cinemaResultView
import com.example.feature_persons.view.searchResult.comicsResultView
import com.example.feature_persons.view.searchResult.movieResultView
import com.example.feature_persons.view.searchResult.personsResultView
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

    val search by searchViewModel.responseSearch

    var historySearch:Response<HistorySearch> by remember { mutableStateOf(Response.Loading()) }
    var historyMovie:Response<HistoryMovie> by remember { mutableStateOf(Response.Loading()) }
    var cinema:Response<List<Cinema>> by remember { mutableStateOf(Response.Loading()) }

    var sortingSelectedTabIndex by remember { mutableStateOf(0) }

    val expandedStateFilm = remember { mutableStateOf(false) }
    val rotationStateFilm by animateFloatAsState(
        targetValue = if (expandedStateFilm.value) 180f else 0f
    )

    val expandedStateCinema = remember { mutableStateOf(false) }
    val rotationStateCinema by animateFloatAsState(
        targetValue = if (expandedStateCinema.value) 180f else 0f
    )

    val expandedStatePerson = remember { mutableStateOf(false) }
    val rotationStatePerson by animateFloatAsState(
        targetValue = if (expandedStatePerson.value) 180f else 0f
    )

    val expandedStateComics = remember { mutableStateOf(false) }
    val rotationStateComics by animateFloatAsState(
        targetValue = if (expandedStateComics.value) 180f else 0f
    )

    val expandedStateCharactersRickAndMorty = remember { mutableStateOf(false) }
    val rotationStateCharactersRickAndMorty by animateFloatAsState(
        targetValue = if (expandedStateComics.value) 180f else 0f
    )

    var movieTotal by remember { mutableStateOf(0) }
    var personTotal by remember { mutableStateOf(0) }
    var comicsTotal by remember { mutableStateOf(0) }
    var charactersRickAndMortyTotal by remember { mutableStateOf(0) }

    val sortingOrderFilm by searchViewModel.responseOrderFilm
    val sortingRatingToFilm by searchViewModel.responseRatingToFilm
    val sortingRatingFromFilm by searchViewModel.responseRatingFromFilm

    val has3DCinema by searchViewModel.responseCinemaHas3D
    val has4DCinema by searchViewModel.responseCinemaHas4D
    val hasImaxCinema by searchViewModel.responseCinemaHasImax

    val rickAndMortyCharacterStatus by searchViewModel.responseRiakAndMortyStatusCharacters

    val charactersRickAndMortySource = searchViewModel.getCharactersRickAndMorty(
        search = search,
        status = rickAndMortyCharacterStatus?.title ?: ""
    ).collectAsLazyPagingItems()

    val movie = searchViewModel.getFilm(
        keyword = search,
        order = sortingOrderFilm.name,
        ratingFrom = sortingRatingFromFilm,
        ratingTo = sortingRatingToFilm
    ).collectAsLazyPagingItems()

    val comicsMarvel = searchViewModel.getComicsMarvel(
        search = search
    ).collectAsLazyPagingItems()

    val persons = searchViewModel.getSearchPerson(
        name = search
    ).collectAsLazyPagingItems()

    val comics = searchViewModel.getComicsMarvel(
        search = search
    ).collectAsLazyPagingItems()

    searchViewModel.responseCharactersRiakAndMortyTotal.onEach {
        charactersRickAndMortyTotal = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseHistorySearch.onEach {
        historySearch = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseHistoryMovie.onEach {
        historyMovie = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.getCinema()
    searchViewModel.responseCinema.onEach {
        cinema = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseMovieTotal.onEach {
        movieTotal = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responsePersonTotal.onEach {
        personTotal = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    searchViewModel.responseComicsTotal.onEach {
        comicsTotal = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    LaunchedEffect(
        key1 = has3DCinema,
        key2 = has4DCinema,
        key3 = hasImaxCinema,
        block = {
            searchViewModel.getCinema(
                search = search,
                has3D = when(has3DCinema){
                    "true" -> true
                    "false" -> false
                    else -> null
                },
                has4D = when(has4DCinema){
                    "true" -> true
                    "false" -> false
                    else -> null
                },
                hasImax = when(hasImaxCinema){
                    "true" -> true
                    "false" -> false
                    else -> null
                }
            )
        }
    )

    LaunchedEffect(key1 = search, block = {
        if (search.isNotEmpty()){
            searchViewModel.getCinema(
                search = search
            )

            searchViewModel.postHistorySearch(
                historySearchItem = HistorySearchItem(
                    id = 0,
                    title = search,
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
                            search = {
                                searchViewModel.updateSearch(it)
                            }
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
                            if (search.isEmpty()){
                                item {
                                    HistorySearchView(
                                        historySearch = historySearch,
                                        search = {
                                            searchViewModel.updateSearch(it)
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
                                    movieTotal = movieTotal,
                                    navController = navController
                                )

                                personsResultView(
                                    person = persons,
                                    navController = navController,
                                    expandedState = expandedStatePerson,
                                    rotationState = rotationStatePerson,
                                    personTotal = personTotal
                                )

                                cinemaResultView(
                                    cinema = cinema,
                                    expandedStateCinema = expandedStateCinema,
                                    rotationStateCinema = rotationStateCinema,
                                    navController = navController
                                )

                                comicsResultView(
                                    comics = comics,
                                    expandedState = expandedStateComics,
                                    rotationState = rotationStateComics,
                                    comicsTotal = comicsTotal
                                )

                                rickAndMortyCharacterResultView(
                                    characters = charactersRickAndMortySource,
                                    expandedState = expandedStateCharactersRickAndMorty,
                                    rotationState = rotationStateCharactersRickAndMorty,
                                    charactersTotal = charactersRickAndMortyTotal
                                )
                            }
                        }
                        1 -> item {
                            SortingView(
                                searchViewModel = searchViewModel
                            )
                        }
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