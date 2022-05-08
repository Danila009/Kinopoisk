package com.example.feature_genre.screen

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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.Genre
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.encodeToString
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenRoute
import com.example.feature_genre.viewModel.GenreViewModel
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@Composable
fun GenreScreen(
    genreViewModel: GenreViewModel,
    navController: NavController,
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val genreCheck = remember { mutableStateOf(ArrayList<Genre>()) }
    val genre = remember { mutableStateOf(listOf<Genre>()) }

    genreViewModel.getFilter()
    genreViewModel.responseFilter.onEach {
        genre.value = it.genres
    }.launchWhenStarted(lifecycleScope, lifecycle)

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
                            genre = encodeToString(genreCheck.value)
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