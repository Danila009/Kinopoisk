package com.example.feature_film_info.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.serial.Season
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute

@Composable
internal fun SeasonView(
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
                    onClick = { navController.navigate(
                        FilmScreenRoute.SerialInfoSeason.base(filmId.toString())
                    ) },
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