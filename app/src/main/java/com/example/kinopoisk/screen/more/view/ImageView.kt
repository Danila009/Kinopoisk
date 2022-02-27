package com.example.kinopoisk.screen.more.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.kinopoisk.screen.filmInfo.viewState.ImageViewState

@Composable
fun ImageView(
    filmId:String,
    imageViewState: ImageViewState
) {
    val context = LocalContext.current
    val filmInfoViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .filmInfoViewModel()

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