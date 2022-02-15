package com.example.kinopoisk.screen.filmInfo.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun SeasonView(
    navController:NavController,
    season:MutableState<Season>,
    filmInfo:MutableState<FilmInfo>,
    filmId:Int
) {
    if (filmInfo.value.serial){
        season.value.total?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Сезоны и серии:",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        color = secondaryBackground
                    )
                    Text(
                        text = "${season.value.total} Сезона",
                        modifier = Modifier.padding(5.dp)
                    )
                }
                TextButton(
                    onClick = { navController.navigate(Screen.SerialInfoSeason.base(filmId.toString())) },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = "Все ->",
                        color = secondaryBackground
                    )
                }
            }
        }
    }
}