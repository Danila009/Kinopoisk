package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.key.StaffInfoScreenKey
import com.example.kinopoisk.screen.main.key.WebScreenKey
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun PersonScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
    search:String
) {
    val check = remember { mutableStateOf(false) }

    val person = mainViewModel.getSearchPerson(
        name = search.ifEmpty { "a" }
    ).collectAsLazyPagingItems()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = primaryBackground
    ) {
        LazyColumn(content = {
            items(person){ item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(primaryBackground)
                        .clickable{
                            navController.navigate(Screen.StaffInfo.base(
                                staffId = item?.kinopoiskId.toString(),
                                key = Converters().encodeToString(StaffInfoScreenKey.PERSON)
                            ))
                        },
                    shape = AbsoluteRoundedCornerShape(15.dp),
                    elevation = 8.dp
                ) {
                    Row {
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
                                .height(150.dp)
                                .width(230.dp)
                        )
                        Column {
                            Text(
                                text = item?.nameRu.toString(),
                                modifier = Modifier.padding(5.dp),
                                color = secondaryBackground
                            )
                            TextButton(onClick = {
                                item?.webUrl?.let {
                                    navController.navigate(Screen.WebScreen.base(
                                        keyString = Converters().encodeToString(WebScreenKey.PERSON),
                                        webUrl = it
                                    ))
                                }
                            }) {
                                Text(
                                    text = "Кинопоиск ->",
                                    modifier = Modifier.padding(5.dp),
                                    color = secondaryBackground
                                )
                            }
                        }
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

            person.apply {
                when{
                    loadState.refresh is LoadState.Loading -> check.value = false

                    loadState.append is LoadState.Loading -> check.value = true
                }
            }
        })
    }
}