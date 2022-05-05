package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.profile

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
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun FavoriteFilmView(
    navController: NavController,
    userInfo: UserInfo
) {
    if (userInfo.favoritFilm.isNotEmpty()){
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
            items(userInfo.favoritFilm) { item ->
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(
                            FilmScreenRoute.FilmInfo.base(
                                filmId = item.kinopoiskId.toString()
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