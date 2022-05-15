package com.example.feature_persons.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.replaceRange
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute

@Composable
internal fun CinemaView(
    navController: NavController,
    cinema: Response<List<Cinema>>
) {
    if (cinema !is Response.Error){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cinema",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = secondaryBackground
            )

            TextButton(
                onClick = { navController.navigate(CinemaScreenRoute.CinemaMap.route) },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Все ->",
                    color = secondaryBackground
                )
            }
        }

        LazyRow(content = {
            if (cinema is Response.Success){
                items(cinema.data!!){ item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                CinemaScreenRoute.CinemaInfo.base(
                                    cinemaId = item.id
                                )
                            )
                        }
                    ) {
                        SubcomposeAsyncImage(
                            model = item.icon,
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
                                item.title,
                                50
                            ),
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }else {
                items(10){
                    BaseRawListShimmer(
                        imageWidth = 300.dp,
                        imageHeight = 200.dp
                    )
                }
            }
        })
    }
}