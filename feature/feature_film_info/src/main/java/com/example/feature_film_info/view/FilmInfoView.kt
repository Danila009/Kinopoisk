package com.example.feature_film_info.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.serial.Season
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
internal fun FilmInfoView(
    filmInfo:MutableState<FilmInfo>,
    filmId:Int,
    navController:NavController,
    season:MutableState<Season>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (filmInfo.value.posterUrlPreview != ""){
            Image(
                painter = rememberImagePainter(
                    data = filmInfo.value.posterUrlPreview,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(350.dp)
                    .width(250.dp)
            )
        }
        LazyRow(content = {
            items(filmInfo.value.genres){
                Text(
                    text = it.genre,
                    modifier = Modifier.padding(5.dp)
                )
            }
        })
        LazyRow(content = {
            items(filmInfo.value.countries){
                Text(
                    text = "${it.country} ",
                    modifier = Modifier.padding(bottom =  5.dp)
                )
            }
        })
    }

    SeasonView(
        navController = navController,
        season = season,
        filmInfo = filmInfo,
        filmId = filmId
    )

    filmInfo.value.slogan?.let {
        Text(
            text = "Слоган:",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = secondaryBackground
        )

        Text(
            text = it,
            modifier = Modifier.padding(5.dp)
        )
    }

    filmInfo.value.description?.let {
        Text(
            text = "Описание:",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = secondaryBackground
        )
        Text(
            text = it,
            modifier = Modifier.padding(5.dp)
        )
    }
}