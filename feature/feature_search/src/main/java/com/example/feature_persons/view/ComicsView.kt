package com.example.feature_persons.view

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
import com.example.core_network_domain.model.marvel.comics.Result
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.comicsNavGraph.ComicsScreenRoute
import com.example.core_utils.state.ComicsState

@Composable
fun ComicsView(
    navController: NavController,
    comicsState: ComicsState,
    comicsMarvel: LazyPagingItems<Result>
){
    when(comicsState){
        ComicsState.MARVEL -> {
            if (
                comicsMarvel.loadState.refresh !is LoadState.Error
                || comicsMarvel.loadState.append !is LoadState.Error
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Comics:",
                        modifier = Modifier.padding(5.dp),
                        color = secondaryBackground,
                        fontWeight = FontWeight.Bold
                    )

                    TextButton(
                        onClick = {
                            navController.navigate(ComicsScreenRoute.ComicsScreen.arguments(
                            comicsState = ComicsState.MARVEL.name
                        )) },
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Все ->",
                            color = secondaryBackground
                        )
                    }
                }

                LazyRow(content = {
                    items(comicsMarvel){ item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            SubcomposeAsyncImage(
                                model = "${item?.thumbnail?.path}.${item?.thumbnail?.extension}",
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .height(133.dp)
                                    .width(204.dp)
                            ) {
                                val state = painter.state
                                if (
                                    state is AsyncImagePainter.State.Loading ||
                                    state is AsyncImagePainter.State.Error
                                ) {
                                    ImageShimmer(
                                        imageHeight = 133.dp,
                                        imageWidth = 204.dp
                                    )
                                } else {
                                    SubcomposeAsyncImageContent()
                                }
                            }

                            Text(
                                text = item?.title.toString(),
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    item {
                        if (
                            comicsMarvel.loadState.refresh is LoadState.Loading
                            || comicsMarvel.loadState.append is LoadState.Loading
                        ){
                            BaseRawListShimmer(
                                imageHeight = 133.dp,
                                imageWidth = 204.dp
                            )
                        }
                    }
                })
            }
        }
    }
}