package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@ExperimentalFoundationApi
@Composable
fun FilmsScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
    order:String = "RATING",
    type:String = "ALL",
    ratingFrom:Int = 0,
    ratingTo:Int = 10,
    yearFrom:Int = 1000,
    yearTo:Int = 3000,
) {
    val search = remember { (mutableStateOf("")) }
    val check = remember { mutableStateOf(false) }
    val filmList = mainViewModel.getFilm(
        order = order,
        type = type,
        ratingFrom = ratingFrom,
        ratingTo = ratingTo,
        yearFrom = yearFrom,
        yearTo = yearTo,
        keyword = search.value,
    ).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    TextField(
                        value = search.value,
                        onValueChange = {
                            search.value = it
                        }, placeholder = {
                            Text(text = "Search")
                        }, leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = secondaryBackground
                            )
                        }, keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ), shape = AbsoluteRoundedCornerShape(5.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = secondaryBackground,
                            backgroundColor = primaryBackground
                        ), trailingIcon = {
                            if (search.value.isNotEmpty()){
                                IconButton(onClick = { search.value = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        tint = secondaryBackground
                                    )
                                }
                            }
                        }
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Sorting.route) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
                    items(filmList){ item ->
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
                                Text(
                                    text = item?.nameRu.toString(),
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }

                    item {
                        if (check.value){
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    color = secondaryBackground
                                )
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

                    filmList.apply {
                        when{
                            loadState.refresh is LoadState.Loading -> check.value = false

                            loadState.append is LoadState.Loading -> check.value = true
                        }
                    }
                })
            }
        }
    )
}