package com.example.kinopoisk.screen.main.sortingScreen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.filmInfo.Genre
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.sortingFilmNavGraph.constants.SortingScreenRoute
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@Composable
fun GenreScreen(
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
) {
    val context = LocalContext.current
    val mainViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .mainViewModel()

    val genreCheck = remember { mutableStateOf(ArrayList<Genre>()) }
    val genre = remember { mutableStateOf(listOf<Genre>()) }

    mainViewModel.getFilter()
    mainViewModel.responseFilter.onEach {
        genre.value = it.genres
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = { Text(text = "Genre") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(SortingScreenRoute.SortingFilm.base())
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    TextButton(onClick = {
                        navController.navigate(SortingScreenRoute.SortingFilm.base(
                            genre = Converters().encodeToString(genreCheck.value)
                        ))
                    }) {
                        Text(
                            text = "Применить",
                            color = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ){
                LazyColumn(content = {
                    items(genre.value){ item ->
                        val checkGenre = remember { mutableStateOf(false) }
                        if (checkGenre.value){
                            genreCheck.value.add(item)
                        }else{
                            genreCheck.value.remove(item)
                        }
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        checkGenre.value = !checkGenre.value
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.genre,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Checkbox(
                                    checked = checkGenre.value,
                                    onCheckedChange = {checkGenre.value = it},
                                    modifier = Modifier.padding(5.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = secondaryBackground
                                    )
                                )
                            }
                            Divider()
                        }
                    }
                })
            }
        }
    )
}