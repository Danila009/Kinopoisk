package com.example.feature_persons.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.core_network_domain.model.movie.FilmItem
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.replaceRange
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute

@Composable
internal fun MovieView(
    navController: NavController,
    movie: LazyPagingItems<FilmItem>
) {
    if (
        movie.loadState.refresh !is LoadState.Error
        || movie.loadState.append !is LoadState.Error
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Movie:",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = secondaryBackground
            )

            TextButton(
                onClick = { navController.navigate(MainScreenRoute.MainRoute.Films.route) },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Все ->",
                    color = secondaryBackground
                )
            }
        }

        LazyRow(content = {
            items(movie){ item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        navController.navigate(
                            FilmScreenRoute.FilmInfo.base(
                                filmId = item?.kinopoiskId.toString()
                            )
                        )
                    }
                ) {
                    SubcomposeAsyncImage(
                        model = item?.posterUrlPreview,
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
                            ImageShimmer(
                                imageHeight = 180.dp,
                                imageWidth = 140.dp
                            )
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }
                    Text(
                        text = replaceRange(
                            item?.nameRu.toString(),
                            50
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

            item {
                if (
                    movie.loadState.refresh is LoadState.Loading
                    || movie.loadState.append is LoadState.Loading
                ){
                    BaseRawListShimmer()
                }
            }
        })
    }
}