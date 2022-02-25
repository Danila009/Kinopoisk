package com.example.kinopoisk.screen.filmInfo

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.filmInfo.Budget
import com.example.kinopoisk.api.model.filmInfo.Fact
import com.example.kinopoisk.api.model.filmInfo.SequelAndPrequel
import com.example.kinopoisk.api.model.filmInfo.Similar
import com.example.kinopoisk.api.model.filmInfo.distribution.Distribution
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.api.model.user.history.History
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmInfo.view.*
import com.example.kinopoisk.screen.filmInfo.viewState.ImageViewState
import com.example.kinopoisk.screen.main.key.WebScreenKey
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Constants.TOKEN_SHARED
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FilmInfoScreen(
    filmInfoViewModel: FilmInfoViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    filmId: Int,
) {
    val context = LocalContext.current
    val checkWeb = remember { mutableStateOf(false) }
    val userFavoriteCheck = remember { mutableStateOf(false) }
    val filmInfo = remember { mutableStateOf(FilmInfo()) }
    val budget = remember { mutableStateOf(Budget()) }
    val fact = remember { mutableStateOf(Fact()) }
    val staff = remember { mutableStateOf(listOf<Staff>()) }
    val similar = remember { mutableStateOf(Similar()) }
    val distribution = remember { mutableStateOf(Distribution()) }
    val sequelAndPrequel = remember { mutableStateOf(listOf<SequelAndPrequel>()) }
    val season = remember { mutableStateOf(Season()) }
    val shop = remember { mutableStateOf(Shop()) }
    val purchaseCheck = remember { mutableStateOf(false) }
    val shopCheck = remember { mutableStateOf(false) }
    val token = context.getSharedPreferences(TOKEN_SHARED, Context.MODE_PRIVATE).getString(TOKEN_SHARED, "")

    filmInfoViewModel.getFilmInfo(filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getBudget(filmId)
    filmInfoViewModel.responseBudget.onEach {
        budget.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getFact(filmId)
    filmInfoViewModel.responseFact.onEach {
        fact.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getStaff(filmId)
    filmInfoViewModel.responseStaff.onEach {
        staff.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getSimilar(filmId)
    filmInfoViewModel.responseSimilar.onEach {
        similar.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getSequelAndPrequel(filmId)
    filmInfoViewModel.responseSequelAndPrequel.onEach {
        sequelAndPrequel.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getSeason(filmId)
    filmInfoViewModel.responseSeason.onEach {
        season.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getDistribution(filmId)
    filmInfoViewModel.responseDistribution.onEach {
        distribution.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getUserFavoriteCheck(filmId)
    filmInfoViewModel.responseUserFavoriteCheck.onEach {
        userFavoriteCheck.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getShopCheck(filmId)
    filmInfoViewModel.responseShopCheck.onEach {
        shopCheck.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getShopId(filmId)
    filmInfoViewModel.responseShopId.onEach {
        shop.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getPurchase(idKinopoisk = filmId)
    filmInfoViewModel.responsePurchaseCheck.onEach {
        purchaseCheck.value = it
    }.launchWhenStarted(lifecycleScope)

    val image = filmInfoViewModel.getImage(
        id = filmId,
        type = ImageViewState.STILL.name
    ).collectAsLazyPagingItems()

    val review = filmInfoViewModel.getReview(
        id = filmId
    ).collectAsLazyPagingItems()

    if (filmInfo.value.nameRu.isNotEmpty()){
        LaunchedEffect(key1 = Unit, block = {
            filmInfoViewModel.postHistory(
                history = History(
                    date = Converters().getCurrentTime(),
                    kinopoiskId = filmInfo.value.kinopoiskId,
                    nameRu = filmInfo.value.nameRu,
                    ratingKinopoisk = filmInfo.value.ratingKinopoisk,
                    posterUrlPreview = filmInfo.value.posterUrlPreview
                )
            )
        })
    }
    if (checkWeb.value){
        LaunchedEffect(key1 = Unit, block = {
            filmInfo.value.webUrl?.let {
                navController.navigate(Screen.WebScreen.base(
                    filmId = filmId.toString(),
                    keyString = Converters().encodeToString(WebScreenKey.FILM),
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
                        Text(text = filmInfo.value.nameRu.toString())
                    }, navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.Main.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    }, actions = {
                        if (token!!.isNotEmpty()){
                            IconButton(onClick = {
                                if (userFavoriteCheck.value){
                                    filmInfoViewModel.deleteFavoriteFilm(
                                        kinopoiskId = filmId
                                    )
                                    userFavoriteCheck.value = false
                                }else{
                                    filmInfoViewModel.postFavoriteFilm(
                                        FilmItem(
                                            kinopoiskId = filmInfo.value.kinopoiskId,
                                            imdbId = filmInfo.value.imdbId,
                                            nameRu = filmInfo.value.nameRu,
                                            nameEn = filmInfo.value.nameEn,
                                            nameOriginal = filmInfo.value.nameOriginal,
                                            ratingImdb = filmInfo.value.ratingImdb,
                                            ratingKinopoisk = filmInfo.value.ratingKinopoisk,
                                            year = filmInfo.value.year,
                                            posterUrl = filmInfo.value.posterUrl,
                                            posterUrlPreview = filmInfo.value.posterUrlPreview,
                                            type = filmInfo.value.type
                                        )
                                    )
                                    userFavoriteCheck.value = true
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = if (userFavoriteCheck.value) Color.Red else Color.Gray
                                )
                            }
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

                                if (shopCheck.value){
                                    ShopView(
                                        filmInfoViewModel = filmInfoViewModel,
                                        shop = shop.value,
                                        checkPurchase = purchaseCheck.value
                                    )
                                }

                                RatingView(
                                    filmInfo = filmInfo
                                )

                                StaffView(
                                    staff = staff,
                                    navController = navController,
                                    filmId = filmId.toString()
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
                    })
                }
            }
        )
    }
}