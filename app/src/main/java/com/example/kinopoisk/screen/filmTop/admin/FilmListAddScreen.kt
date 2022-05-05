package com.example.kinopoisk.screen.filmTop.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants.MainScreenConstants.Route.MAIN_ROUTE
import com.example.kinopoisk.screen.cinema.view.BaseTextField
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.utils.Converters
import java.util.ArrayList

@Composable
fun FilmListAddScreen(
    navController: NavController,
    filmListString: String? = null
) {

    val context = LocalContext.current
    val filmTopViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .filmTopViewModel()

    val titleFilmList = remember { mutableStateOf("") }
    val filmList = remember { mutableStateOf(ArrayList<FilmItem>()) }
    filmListString?.let {
        filmList.value = Converters().decodeFromString(filmListString)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Add list film")
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(MAIN_ROUTE) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                LazyColumn(content = {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BaseTextField(
                                label = "Title",
                                value = titleFilmList
                            )
                        }
                    }
                    items(filmList.value){ item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        FilmScreenRoute.FilmInfo.base(
                                            item.kinopoiskId.toString()
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
                                        data = item.posterUrl,
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
                                        text = item.nameRu.toString(),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    IconButton(onClick = {
                                        filmList.value.remove(item)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = null,
                                            tint = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    }
                    item {
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 5.dp
                                ), shape = AbsoluteRoundedCornerShape(15.dp),
                            onClick = {
                                navController.navigate(PlaylistScreenRoute.FilmListItemAdd.route)
                            }) {
                            Text(text = "Add film")
                        }

                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 5.dp
                                ), shape = AbsoluteRoundedCornerShape(15.dp),
                            onClick = {
                                if (titleFilmList.value.isNotEmpty() || filmList.value.isNotEmpty()){
                                    filmTopViewModel.postFilmList(
                                        AdminFilmList(
                                            date = Converters().getCurrentTime(),
                                            title = titleFilmList.value,
                                            movies = filmList.value
                                        )
                                    )
                                    navController.navigate(MAIN_ROUTE)
                                }
                            }) {
                            Text(text = "Add list film")
                        }
                    }
                })
            }
        }
    )
}