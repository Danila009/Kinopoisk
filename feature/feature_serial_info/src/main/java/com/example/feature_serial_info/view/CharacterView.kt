package com.example.feature_serial_info.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.core_network_domain.enum.StatusCharacterRickAndMorty
import com.example.core_network_domain.model.rickAndMorty.CharacterItem

@Composable
fun CharacterRickAndMortyView(
    characterItem: CharacterItem
) {
    val primaryBackground = Color(0xFF3C3E44)

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
                    data = characterItem.image,
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
                    text = characterItem.name,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(15.dp)
                    ){
                        drawCircle(
                            color = when(characterItem.status){
                                StatusCharacterRickAndMorty.Alive -> Color.Green
                                StatusCharacterRickAndMorty.Dead -> Color.Red
                                StatusCharacterRickAndMorty.unknown -> Color.Gray
                            }
                        )
                    }
                    Text(
                        text = "${characterItem.status.name}-${characterItem.species}",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = characterItem.gender,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = characterItem.created,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}