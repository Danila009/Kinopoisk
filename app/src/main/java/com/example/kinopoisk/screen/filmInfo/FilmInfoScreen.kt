package com.example.kinopoisk.screen.filmInfo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.Budget
import com.example.kinopoisk.api.model.filmInfo.Fact
import com.example.kinopoisk.api.model.filmInfo.SequelAndPrequel
import com.example.kinopoisk.api.model.filmInfo.Similar
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmInfo.view.*
import com.example.kinopoisk.screen.filmInfo.viewState.ImageViewState
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
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
    val checkWeb = remember { mutableStateOf(false) }
    val filmInfo = remember { mutableStateOf(FilmInfo()) }
    val budget = remember { mutableStateOf(Budget()) }
    val fact = remember { mutableStateOf(Fact()) }
    val staff = remember { mutableStateOf(listOf<Staff>()) }
    val similar = remember { mutableStateOf(Similar()) }
    val sequelAndPrequel = remember { mutableStateOf(listOf<SequelAndPrequel>()) }
    val season = remember { mutableStateOf(Season()) }

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

    val image = filmInfoViewModel.getImage(
        id = filmId,
        type = ImageViewState.STILL.name
    ).collectAsLazyPagingItems()

    val review = filmInfoViewModel.getReview(
        id = filmId
    ).collectAsLazyPagingItems()

    if (checkWeb.value){
        WebView(url = filmInfo.value.webUrl!!)
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
                                    review = review
                                )

                                ImageView(
                                    navController = navController,
                                    image = image
                                )

                                FactView(
                                    fact = fact
                                )

                                BudgetView(
                                    budget = budget
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