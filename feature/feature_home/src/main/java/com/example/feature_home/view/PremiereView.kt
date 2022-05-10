package com.example.feature_home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getTime
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute

@Composable
internal fun PremiereView(
    navController: NavController,
    premiere: Premiere
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Премьеры в кино:",
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
        if(premiere.items.isNotEmpty()){
            items(premiere.items) { item ->
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(
                            FilmScreenRoute.FilmInfo.base(
                                filmId = item.kinopoiskId.toString()
                            )
                        )
                    }
                ) {
                    SubcomposeAsyncImage(
                        model = item.posterUrl,
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
                        text = getTime(item.premiereRu),
                        modifier = Modifier.padding(
                            top = 5.dp,
                            start = 22.dp,
                            bottom = 5.dp
                        )
                    )
                }
            }
        }else{
            items(10){
                BaseRawListShimmer()
            }
        }
    })
}