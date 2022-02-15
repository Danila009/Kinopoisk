package com.example.kinopoisk.screen.filmInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.seasons.Episode
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@Composable
fun SerialInfoSeasonScreen(
    filmInfoViewModel: FilmInfoViewModel = hiltViewModel(),
    navController: NavController,
    filmId:Int,
    lifecycleScope: LifecycleCoroutineScope
) {
    val season = remember { mutableStateOf(Season()) }
    val seasonEpisode = remember { mutableStateOf(ArrayList<Episode>()) }
    val filmInfo = remember { mutableStateOf(FilmInfo()) }

    filmInfoViewModel.getFilmInfo(filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope)

   filmInfoViewModel.getSeason(filmId)
   filmInfoViewModel.responseSeason.onEach {
       season.value = it
   }.launchWhenStarted(lifecycleScope)

    if (season.value.total.toString().isNotEmpty()){
        season.value.items.forEach { item ->
            item.episodes.forEach {
                seasonEpisode.value.add(it)
            }
        }
    }
    if (seasonEpisode.value.isNotEmpty()){
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = primaryBackground,
                    elevation = 8.dp,
                    title = {
                        Text(text = filmInfo.value.nameRu.toString())
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Screen.FilmInfo.base(filmId.toString())) }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    }
                )
            }, content = {
                LazyColumn(content = {
                    items(seasonEpisode.value){
                        Text(text = it.toString())
                    }
                })
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primaryBackground
                ) {
                    LazyColumn(content = {
                        items(seasonEpisode.value){ item ->
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "${item.seasonNumber} Сезон ${item.episodeNumber} Серия",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )
                                        item.nameRu?.let {
                                            Text(
                                                text = "Название серии:",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.Bold,
                                                color = secondaryBackground
                                            )
                                            Text(
                                                text = it,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                        item.synopsis?.let {
                                            Text(
                                                text = "Описание серии:",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.Bold,
                                                color = secondaryBackground
                                            )
                                            Text(
                                                text = it,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                        item.releaseDate?.let {
                                            Text(
                                                text = "Дата выхода серии:",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.Bold,
                                                color = secondaryBackground
                                            )
                                            Text(
                                                text = Converters().getTime(it),
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                                Divider(
                                    thickness = 2.dp,
                                    color = Color.White
                                )
                            }
                        }
                    })
                }
            }
        )
    }
}