package com.example.feature_profile.view

import androidx.compose.foundation.Image
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
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.movie.Movie
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute

@Composable
fun FavoriteFilmView(
    navController: NavController,
    movie: Movie
) {
    if (movie.items.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Favorite film:",
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
            items(movie.items) { item ->
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(
                            FilmScreenRoute.FilmInfo.base(
                                filmId = item.kinopoiskId!!
                            )
                        )
                    }
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = item.posterUrl,
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
        })
    }
}