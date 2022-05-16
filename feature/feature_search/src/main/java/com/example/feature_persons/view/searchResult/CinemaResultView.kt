package com.example.feature_persons.view.searchResult

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute

@ExperimentalMaterialApi
internal fun LazyListScope.cinemaResultView(
    navController: NavController,
    expandedStateCinema:MutableState<Boolean>,
    rotationStateCinema:Float,
    cinema:Response<List<Cinema>>
){
    item {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            backgroundColor = primaryBackground,
            onClick = {
                expandedStateCinema.value = !expandedStateCinema.value
            }
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Cinema ${cinema.data?.size}",
                        modifier = Modifier.padding(5.dp),
                        color = secondaryBackground
                    )

                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationStateCinema),
                        onClick = {
                            expandedStateCinema.value = !expandedStateCinema.value
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                }

                if (expandedStateCinema.value){
                    cinema.data?.let { items ->
                        if(items.isNotEmpty()){
                            items.forEach { item ->
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.clickable {
                                        navController.navigate(
                                            CinemaScreenRoute.CinemaInfo.base(
                                                cinemaId = item.id
                                            )
                                        )
                                    }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        SubcomposeAsyncImage(
                                            model = item.icon,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .height(100.dp)
                                                .width(150.dp)
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

                                        Text(
                                            text = item.title,
                                            modifier = Modifier.padding(5.dp)
                                        )
                                    }
                                    Divider()
                                }
                            }
                        }else {
                            FilmListShimmer()
                        }
                    }
                }
            }
        }
    }
}