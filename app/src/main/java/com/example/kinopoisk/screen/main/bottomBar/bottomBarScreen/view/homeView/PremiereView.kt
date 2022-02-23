package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.homeView

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
import com.example.kinopoisk.api.model.premiere.Premiere
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun PremiereView(
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
        items(premiere.items) { item ->
            Column(
                modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.FilmInfo.base(
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
                Text(
                    text = Converters().getTime(item.premiereRu),
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 22.dp,
                        bottom = 5.dp
                    )
                )
            }
        }
    })
}