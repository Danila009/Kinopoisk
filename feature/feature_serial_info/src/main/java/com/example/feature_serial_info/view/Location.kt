package com.example.feature_serial_info.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.rickAndMorty.LocationItem

@Composable
fun LocationRickAndMortyView(
    locationItem: LocationItem
) {
    val primaryBackground = Color(0xFF3C3E44)

    val planetUrl = "" +
            "https://avatars.mds.yandex.net/i?id=3d3091212d4beb854141956747688432_l-5316602-images-thumbs&n=13"

    Card(
        backgroundColor = primaryBackground,
        shape = AbsoluteRoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 5.dp
    ) {
        Row {
            Image(
                painter = rememberImagePainter(
                    data = planetUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(AbsoluteRoundedCornerShape(15.dp))
            )
            Column {
                Text(
                    text = locationItem.name,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = locationItem.type,
                    modifier = Modifier.padding(5.dp)
                )

                Text(
                    text = locationItem.dimension,
                    modifier = Modifier.padding(5.dp)
                )

                Text(
                    text = locationItem.created,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}