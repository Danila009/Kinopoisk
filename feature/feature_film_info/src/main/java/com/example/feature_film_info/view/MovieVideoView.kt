package com.example.feature_film_info.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.movie.video.Video
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.VideoPlayerView

@Composable
fun MovieVideoView(
    video: Video
) {
    if (video.items.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Videos",
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
            items(video.items){ item ->
                VideoPlayerView(
                    url = item.videoUrl,
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(5.dp)
                        .clip(AbsoluteRoundedCornerShape(15.dp))
                )
            }
        })
    }
}