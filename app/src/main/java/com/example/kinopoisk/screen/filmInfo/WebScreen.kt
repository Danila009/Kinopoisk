package com.example.kinopoisk.screen.filmInfo

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmInfo.view.WebView
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@Composable
fun WebScreen(
    filmInfoViewModel: FilmInfoViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    filmId:Int
) {
    val filmInfo = remember { mutableStateOf(FilmInfo()) }

    filmInfoViewModel.getFilmInfo(filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = Converters().replaceRange(
                        filmInfo.value.webUrl.toString(),
                        50
                    )
                ) }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(
                        Screen.FilmInfo.base(filmId.toString())
                    ) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            filmInfo.value.webUrl?.let {
                WebView(url = it)
            }
        }
    )
}