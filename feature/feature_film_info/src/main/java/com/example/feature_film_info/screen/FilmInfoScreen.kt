package com.example.feature_film_info.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.MovieItem
import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_network_domain.model.movie.SequelAndPrequel
import com.example.core_network_domain.model.movie.Similar
import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_network_domain.model.movie.history.HistoryMovieItem
import com.example.core_network_domain.model.serial.Season
import com.example.core_network_domain.model.movie.staff.Staff
import com.example.core_network_domain.model.movie.video.Video
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getDate
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.feature_film_info.view.*
import com.example.feature_film_info.viewModel.FilmInfoViewModel
import com.example.feature_film_info.viewState.ImageViewState
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
@Composable
fun FilmInfoScreen(
    navController: NavController,
    filmId: Int,
    filmInfoViewModel:FilmInfoViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val checkWeb = remember { mutableStateOf(false) }
    val filmInfo = remember { mutableStateOf(FilmInfo()) }
    val budget = remember { mutableStateOf(Budget()) }
    val fact = remember { mutableStateOf(Fact()) }
    val staff = remember { mutableStateOf(listOf<Staff>()) }
    val similar = remember { mutableStateOf(Similar()) }
    val distribution = remember { mutableStateOf(Distribution()) }
    val sequelAndPrequel = remember { mutableStateOf(listOf<SequelAndPrequel>()) }
    val season = remember { mutableStateOf(Season()) }
    var award by remember { mutableStateOf(Award()) }
    var statusRegistration by remember { mutableStateOf(false) }
    var faq by remember { mutableStateOf(FAQ()) }
    var wikipediaFilmInfo by remember { mutableStateOf(Wikipedia()) }
    var movieVide by remember { mutableStateOf(Video()) }

    filmInfoViewModel.getFilmInfo(filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getBudget(filmId)
    filmInfoViewModel.responseBudget.onEach {
        budget.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getFact(filmId)
    filmInfoViewModel.responseFact.onEach {
        fact.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getStaff(filmId)
    filmInfoViewModel.responseStaff.onEach {
        staff.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getSimilar(filmId)
    filmInfoViewModel.responseSimilar.onEach {
        similar.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getSequelAndPrequel(filmId)
    filmInfoViewModel.responseSequelAndPrequel.onEach {
        sequelAndPrequel.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getSeason(filmId)
    filmInfoViewModel.responseSeason.onEach {
        season.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    filmInfoViewModel.getDistribution(filmId)
    filmInfoViewModel.responseDistribution.onEach {
        distribution.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    filmInfoViewModel.responseStatusRegistration.onEach {
        statusRegistration = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    filmInfoViewModel.getMovieVideo(filmId)
    filmInfoViewModel.responseMovieVideo.onEach {
        movieVide = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    val image = filmInfoViewModel.getImage(
        id = filmId,
        type = ImageViewState.STILL.name
    ).collectAsLazyPagingItems()

    val review = filmInfoViewModel.getReview(
        id = filmId
    ).collectAsLazyPagingItems()

    filmInfo.value.imdbId?.let { imdbId ->
        filmInfoViewModel.getFilmAward(imdbId)
        filmInfoViewModel.responseFilmAward.onEach {
            award = it
        }.launchWhenStarted(lifecycleScope, lifecycle)

        filmInfoViewModel.getFilmFAQ(imdbId)
        filmInfoViewModel.responseFilmFAQ.onEach {
            faq = it
        }.launchWhenStarted(lifecycleScope, lifecycle)

        filmInfoViewModel.getFilmWikipedia(imdbId)
        filmInfoViewModel.responseFilmWikipedia.onEach {
            wikipediaFilmInfo = it
        }.launchWhenStarted(lifecycleScope, lifecycle)
    }

    if (filmInfo.value.nameRu.isNotEmpty()){

        LaunchedEffect(key1 = Unit, block = {
            if (statusRegistration){
                filmInfoViewModel.postHistoryMovie(
                    historyMovieItem = HistoryMovieItem(
                        id = 0,
                        date = getDate(),
                        movie = MovieItem(
                            id = 0,
                            imdbId = filmInfo.value.imdbId,
                            kinopoiskId = filmInfo.value.kinopoiskId,
                            nameEn = filmInfo.value.nameEn,
                            nameOriginal = filmInfo.value.nameOriginal,
                            nameRu = filmInfo.value.nameRu,
                            posterUrl = filmInfo.value.posterUrl,
                            posterUrlPreview = filmInfo.value.posterUrlPreview,
                            ratingImdb = filmInfo.value.ratingImdb,
                            ratingKinopoisk = filmInfo.value.ratingKinopoisk,
                            type = filmInfo.value.type,
                            year = filmInfo.value.year,
                        )
                    )
                )
            }
        })
        if (checkWeb.value){
            LaunchedEffect(key1 = Unit, block = {
                filmInfo.value.webUrl?.let {
                    navController.navigate(FilmScreenRoute.WebScreen.base(
                        webUrl = it
                    ))
                }
            })
        }else{
            Scaffold(
                topBar = {
                    TopAppBar(
                        elevation = 8.dp,
                        backgroundColor = primaryBackground,
                        title = {
                            Text(text = filmInfo.value.nameRu)
                        }, navigationIcon = {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = null
                                )
                            }
                        }, actions = {
                            IconButton(onClick = {
                                if (statusRegistration){
                                    TODO()
                                }else{
                                    navController.navigate(
                                        MainScreenRoute.MainRoute.Profile.route
                                    )
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
//                                        tint = if (userFavoriteCheck.value) Color.Red else Color.Gray
                                )
                            }
                        }
                    )
                }, content = {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        backgroundColor = primaryBackground
                    ) {
                        LazyColumn(content = {
                            item {
                                Column {
                                    FilmInfoView(
                                        filmInfo = filmInfo,
                                        filmId = filmId,
                                        navController = navController,
                                        season = season
                                    )

//                                    if (shopCheck.value){
//                                        ShopView(
//                                            filmInfoViewModel = filmInfoViewModel,
//                                            shop = shop.value,
//                                            checkPurchase = purchaseCheck.value
//                                        )
//                                    }


                                    MovieVideoView(
                                        video = movieVide
                                    )

                                    RatingView(
                                        filmInfo = filmInfo
                                    )

                                    StaffView(
                                        staff = staff,
                                        navController = navController,
                                        filmId = filmId.toString()
                                    )

                                    AwardView(
                                        award = award
                                    )

                                    ReviewView(
                                        navController = navController,
                                        review = review,
                                        filmId = filmId.toString()
                                    )

                                    ImageView(
                                        navController = navController,
                                        image = image,
                                        filmId = filmId.toString()
                                    )

                                    FAQView(
                                        faq = faq
                                    )

                                    FactView(
                                        fact = fact
                                    )

                                    BudgetView(
                                        budget = budget,
                                        distribution = distribution
                                    )

                                    SequelAndPrequelView(
                                        navController = navController,
                                        sequelAndPrequel = sequelAndPrequel
                                    )

                                    SimilarView(
                                        navController = navController,
                                        similar = similar
                                    )

                                    WikipediaFilmInfoView(
                                        wikipedia = wikipediaFilmInfo
                                    )

                                    filmInfo.value.webUrl?.let {
                                        TextButton(
                                            modifier = Modifier.padding(5.dp),
                                            onClick = { checkWeb.value = true }
                                        ) {
                                            Text(
                                                text = "КиноПоиск ->",
                                                color = secondaryBackground,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
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
            )
        }
    }else{
        CircularProgressIndicator()
    }
}