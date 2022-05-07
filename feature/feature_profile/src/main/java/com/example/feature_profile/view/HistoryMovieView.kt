package com.example.feature_profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Composable
fun HistoryMovieView(
    navController: NavController,
    historyMovie: HistoryMovie
) {
    if (historyMovie.items.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "History:",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = secondaryBackground
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
            itemsIndexed(historyMovie.items){index, item ->
                if (index < 10){
                    Column(
                        modifier = Modifier.clickable {
                            navController.navigate(
                                FilmScreenRoute.FilmInfo.base(
                                    filmId = item.movie.kinopoiskId.toString()
                                )
                            )
                        }
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.movie.posterUrlPreview,
                                builder = {
                                    crossfade(true)
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(5.dp)
                                .height(180.dp)
                                .width(140.dp)
                        )
                    }
                }
            }
        })
    }
}