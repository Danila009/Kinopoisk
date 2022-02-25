package com.example.kinopoisk.screen.filmInfo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.filmInfo.SequelAndPrequel
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenRoute
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun SequelAndPrequelView(
    navController:NavController,
    sequelAndPrequel:MutableState<List<SequelAndPrequel>>
) {
    if (sequelAndPrequel.value.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Сиквелы и прикелы:",
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
            items(sequelAndPrequel.value){ item ->
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(
                            FilmScreenRoute.FilmInfo.base(
                                filmId = item.filmId.toString()
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