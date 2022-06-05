package com.example.feature_film_info.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
            itemsIndexed(filmInfo.value.countries){ index, item ->
                Text(text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle()){
                        append(
                            if (index == filmInfo.value.countries.lastIndex) "${item.country},"
                                else "${item.country} "
                        )
                        withStyle(style = SpanStyle(
                            color = secondaryBackground,
                            fontWeight = FontWeight.Bold
                        )){
                            append(
                                if (index == filmInfo.value.countries.lastIndex) " ${filmInfo.value.year} year"
                                    else ""
                            )
                        }
                    }
                })
            }
        })
        LazyRow(content = {
            item {
                Text(
                    text = "${filmInfo.value.ratingAgeLimits}+",
                    modifier = Modifier.padding(5.dp),
                )

                Text(
                    text = "${filmInfo.value.filmLength} Мин.",
                    modifier = Modifier.padding(5.dp)
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

    Row {
        Text(
            text = "Тип",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = filmInfo.value.type.nameRu,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }

    LazyRow(content = {
        item {
            Row {
                Text(
                    text = "Название фильма на русском",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.W100
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = filmInfo.value.nameRu,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.W900
                )
            }
        }
    })

    filmInfo.value.nameEn?.let {
        LazyRow(content = {
            item{
                Row {
                    Text(
                        text = "Название фильма на английском",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = it,
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        })
    }

    filmInfo.value.nameOriginal?.let {
        LazyRow(content = {
            item {
                Row {
                    Text(
                        text = "Оригинальное название фильма",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = it,
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        })
    }

    Row {
        Text(
            text = "3D",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = if (filmInfo.value.has3D) "Имеет" else "Не имеет",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }

    Row {
        Text(
            text = "IMAX",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = if (filmInfo.value.hasImax) "Имеет" else "Не имеет",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }

    Row {
        Text(
            text = "Доступны ли билеты",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = if (filmInfo.value.isTicketsAvailable) "Да" else "Нет",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }

    Row {
        Text(
            text = "Cериал",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = if (filmInfo.value.serial) "Да" else "Нет",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }

    Row {
        Text(
            text = "Короткометражный фильм",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = if (filmInfo.value.shortFilm) "Да" else "Нет",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }

    Row {
        Text(
            text = "Завершенный проект",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W100
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = if (filmInfo.value.completed) "Да" else "Нет",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.W900
        )
    }


    filmInfo.value.editorAnnotation?.let {
        Text(
            text = "Перевод:",
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