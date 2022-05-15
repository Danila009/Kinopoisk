package com.example.feature_film_images.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_ui.animation.ImageShimmer
import com.example.core_utils.state.ImageState
import com.example.feature_film_images.viewModel.FilmImagesViewModel

@ExperimentalFoundationApi
@Composable
fun ImageView(
    filmId:String,
    imageState: ImageState,
    filmImagesViewModel:FilmImagesViewModel
) {

    val screenWidthDp = LocalConfiguration.current.screenWidthDp / 2

    val image = filmImagesViewModel.getFilmImages(
        id = filmId.toInt(),
        type = imageState.name
    ).collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Adaptive(screenWidthDp.dp),content = {
        items(image){ item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = item?.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(150.dp)
                        .width(200.dp)
                ) {
                    val state = painter.state
                    if (
                        state is AsyncImagePainter.State.Loading ||
                        state is AsyncImagePainter.State.Error
                    ) {
                        ImageShimmer(
                            imageHeight = 100.dp,
                            imageWidth = 150.dp
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
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
    })
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