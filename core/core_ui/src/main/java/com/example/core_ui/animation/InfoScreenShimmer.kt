package com.example.core_ui.animation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CinemaInfoScreenShimmer() {

    Column {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageShimmer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(AbsoluteRoundedCornerShape(8.dp))
            )

            TextShimmer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(20.dp)
                    .clip(AbsoluteRoundedCornerShape(8.dp))
            )

            Spacer(
                modifier = Modifier
                    .padding(5.dp)
            )

            Row {
                repeat(4){
                    ImageShimmer(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(AbsoluteRoundedCornerShape(8.dp))
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        repeat(10){

            TextShimmer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(20.dp)
                    .clip(AbsoluteRoundedCornerShape(8.dp))
            )

            Spacer(
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}