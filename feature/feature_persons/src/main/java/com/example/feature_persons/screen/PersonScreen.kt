package com.example.feature_persons.screen

import androidx.compose.foundation.Image
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
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.SearchView
import com.example.core_utils.key.StaffInfoScreenKey
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.feature_persons.viewModel.PersonsViewModel

@Composable
fun PersonScreen(
    navController: NavController,
    personsViewModel: PersonsViewModel
) {
    val check = remember { mutableStateOf(false) }

    val search = remember { mutableStateOf("") }

    val person = personsViewModel.getSearchPerson(
        name = search.value.ifEmpty { "a" }
    ).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    SearchView(
                        search = search
                    )
                }
            )
        }, content = {
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
                                .clickable {
                                    navController.navigate(StaffInfoScreenRoute.StaffInfo.base(
                                        staffId = item?.kinopoiskId.toString(),
                                        key = StaffInfoScreenKey.PERSON.name
                                    ))
                                },
                            shape = AbsoluteRoundedCornerShape(15.dp),
                            backgroundColor = primaryBackground,
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
                                            navController.navigate(FilmScreenRoute.WebScreen.base(
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
    )
}