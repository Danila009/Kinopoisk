package com.example.feature_serial_info.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.serial.Episode
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getTime

@Composable
fun EpisodesRickAndMortyView(
    episode: Episode
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "${episode.seasonNumber} Сезон ${episode.episodeNumber} Серия",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    color = secondaryBackground
                )
                episode.nameRu?.let {
                    Text(
                        text = "Название серии:",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        color = secondaryBackground
                    )
                    Text(
                        text = it,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                episode.synopsis?.let {
                    Text(
                        text = "Описание серии:",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        color = secondaryBackground
                    )
                    Text(
                        text = it,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                episode.releaseDate?.let {
                    Text(
                        text = "Дата выхода серии:",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        color = secondaryBackground
                    )
                    Row {
                        Text(
                            text = getTime(it),
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
        Divider(
            thickness = 2.dp,
            color = Color.White
        )
    }
}