package com.example.feature_comics.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.view.SearchView
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_comics.viewModel.ComicsViewModel
import kotlinx.coroutines.flow.onEach

@ExperimentalFoundationApi
@Composable
fun MarvelComicsView(
    navController: NavController,
    comicsViewModel: ComicsViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val screenWidthDp = LocalConfiguration.current.screenWidthDp / 2
    val search = remember { mutableStateOf("") }

    val responseComicsMarvel:MutableState<Response<ComicsMarvel>> = remember {
        mutableStateOf(Response.Loading()) }

    comicsViewModel.getComicsMarvel(search.value)
    comicsViewModel.responseComicsMarvel.onEach {
        responseComicsMarvel.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchView(search = search)
                },
                actions = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                color = primaryBackground,
                modifier = Modifier.fillMaxSize()
            ) {
                when(responseComicsMarvel.value){
                    is Response.Error -> Unit
                    is Response.Loading -> Unit
                    is Response.Success -> {
                        val data = responseComicsMarvel.value.data!!
                        LazyVerticalGrid(
                            cells = GridCells.Adaptive(screenWidthDp.dp),
                            content = {
                                items(data.data.results){ item ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = rememberImagePainter(
                                                data = "${item.thumbnail?.path}.${item.thumbnail?.extension}",
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
                                            text = item.title.toString(),
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}