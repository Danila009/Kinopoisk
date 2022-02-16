package com.example.kinopoisk.screen.more.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.screen.filmInfo.FilmInfoViewModel
import com.example.kinopoisk.screen.filmInfo.viewState.ImageViewState

@Composable
fun ImageView(
    filmInfoViewModel: FilmInfoViewModel = hiltViewModel(),
    filmId:String,
    imageViewState: ImageViewState
) {
    val image = filmInfoViewModel.getImage(
        id = filmId.toInt(),
        type = imageViewState.name
    ).collectAsLazyPagingItems()

    LazyColumn(content = {
        items(image){ item ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = item?.imageUrl,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(200.dp)
                        .width(350.dp)
                )
            }
        }
    })
}