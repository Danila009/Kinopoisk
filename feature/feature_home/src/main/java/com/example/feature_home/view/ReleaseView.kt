package com.example.feature_home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.model.movie.premiere.ReleaseItem
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getTime
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute

@Composable
internal fun ReleaseView(
    navController: NavController,
    release: LazyPagingItems<ReleaseItem>
) {
    val checkLoading = remember { mutableStateOf(true) }

    release.apply {
        when{
            loadState.refresh is LoadState.Loading -> checkLoading.value = true

            loadState.append is LoadState.Loading -> checkLoading.value = true
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Цифровые релизы:",
            modifier = Modifier.padding(5.dp),
            color = secondaryBackground,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Все ->",
                color = secondaryBackground
            )
        }
    }
    LazyRow(content = {
        items(release) { item ->
            Column(
                modifier = Modifier.clickable {
                    navController.navigate(
                        FilmScreenRoute.FilmInfo.base(
                            filmId = item?.filmId.toString()
                        )
                    )
                }
            ) {
                SubcomposeAsyncImage(
                    model = item?.posterUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(180.dp)
                        .width(140.dp)
                ) {
                    val state = painter.state
                    if (
                        state is AsyncImagePainter.State.Loading ||
                        state is AsyncImagePainter.State.Error
                    ) {
                        ImageShimmer()
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }

                Text(
                    text = getTime(item!!.releaseDate),
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 22.dp,
                        bottom = 5.dp
                    )
                )
            }
        }
        if (checkLoading.value){
            items(3) {
                BaseRawListShimmer()
            }
        }
    })
}