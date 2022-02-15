package com.example.kinopoisk.screen.filmInfo.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun RatingView(
    filmInfo:MutableState<FilmInfo>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Райтинг",
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
        items(6) { item ->
            Card(
                shape = AbsoluteRoundedCornerShape(7.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                Column {
                    when(item){
                        1->{
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )
                                    ){
                                        append("КиноПоиск: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Converters().rating(
                                                rating = filmInfo.value.ratingKinopoisk
                                            )
                                        )
                                    ){
                                        append(filmInfo.value.ratingKinopoisk.toString())
                                    }
                                },modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "${filmInfo.value.ratingKinopoiskVoteCount} оценки",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        2->{
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )
                                    ){
                                        append("IMDB: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Converters().rating(
                                                rating = filmInfo.value.ratingImdb
                                            )
                                        )
                                    ){
                                        append(filmInfo.value.ratingImdb.toString())
                                    }
                                },modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "${filmInfo.value.ratingImdbVoteCount} оценки",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        3->{
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )
                                    ){
                                        append("Райтинг мировых критиков: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Converters().rating(
                                                rating = filmInfo.value.ratingFilmCritics
                                            )
                                        )
                                    ){
                                        append(filmInfo.value.ratingFilmCritics.toString())
                                    }
                                },modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "${filmInfo.value.ratingFilmCriticsVoteCount} оценки",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        4->{
                            Text(
                                text = "Райтинг российских критиков: ${filmInfo.value.ratingRfCritics}",
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight.Bold,
                                color = secondaryBackground
                            )
                            Text(
                                text = "${filmInfo.value.ratingRfCriticsVoteCount} оценки",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        5->{
                            Text(
                                text = "Положительных рецензий: ${filmInfo.value.ratingGoodReview}%",
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight.Bold,
                                color = secondaryBackground
                            )
                            Text(
                                text = "${filmInfo.value.ratingGoodReviewVoteCount} оценки",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    })
}