package com.example.feature_comics.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.SearchView
import com.example.core_utils.common.replaceRange
import com.example.core_utils.navigation.comicsNavGraph.ComicsScreenRoute
import com.example.core_utils.state.ComicsState
import com.example.feature_comics.viewModel.ComicsViewModel

@ExperimentalFoundationApi
@Composable
fun MarvelComicsView(
    navController: NavController,
    comicsViewModel: ComicsViewModel
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp / 2
    val search = remember { mutableStateOf("") }

    val comicsMarvel = comicsViewModel.getComicsMarvel(search.value).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    SearchView(search = {
                        search.value = it
                    })
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                color = primaryBackground,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(screenWidthDp.dp),
                    content = {
                        items(comicsMarvel){ item ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        ComicsScreenRoute.ComicInfoScreen.arguments(
                                            comicId = item?.id!!,
                                            comicsState = ComicsState.MARVEL
                                        )
                                    )
                                }
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = "${item?.thumbnail?.path}." +
                                                "${item?.thumbnail?.extension}",
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(
                                            width = 204.dp,
                                            height = 133.dp
                                        )
                                )

                                Text(
                                    text = replaceRange(item?.title.toString(), 30),
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                )
            }
        }
    )
}

@ExperimentalFoundationApi
private fun <T: Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}