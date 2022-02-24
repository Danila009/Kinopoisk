package com.example.kinopoisk.screen.filmTop.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmTop.viewModel.FilmTopViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import java.util.ArrayList

@Composable
fun FilmListItemAddScreen(
    filmTopViewModel: FilmTopViewModel = hiltViewModel(),
    navController: NavController
) {
    val filmAddList = remember { mutableStateOf(ArrayList<FilmItem>()) }
    val films = filmTopViewModel.getFilm().collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "film list add item")
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.FilmListAdd.base())
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                        vertical = 5.dp
                    ), shape = AbsoluteRoundedCornerShape(15.dp),
                onClick = { navController.navigate(
                    Screen.FilmListAdd.base(
                        filmList = Converters().encodeToString(filmAddList.value)
                    )
                ) }
            ) {
                Text(text = "Add top list")
            }
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                LazyColumn(content = {
                    items(films){ item ->
                        val checkFilm = remember { mutableStateOf(false) }
                        if (checkFilm.value){
                            filmAddList.value.add(item!!)
                        }else{
                            filmAddList.value.remove(item)
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.FilmInfo.base(
                                            item?.kinopoiskId.toString()
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
                                Image(
                                    painter = rememberImagePainter(
                                        data = item?.posterUrl,
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(100.dp)
                                        .clip(AbsoluteRoundedCornerShape(10.dp))
                                )
                                Column {
                                    Text(
                                        text = item?.nameRu.toString(),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Checkbox(
                                        checked = checkFilm.value,
                                        onCheckedChange = { checkFilm.value = it },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = secondaryBackground
                                        )
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