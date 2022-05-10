package com.example.core_ui.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaseRawListShimmer(
    imageHeight:Dp = 180.dp,
    imageWidth:Dp = 140.dp
) {
    val brush = baseAnimationShimmer()

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        //Image
        ImageShimmer(
            imageHeight = imageHeight,
            imageWidth = imageWidth
        )
        Spacer(modifier = Modifier.padding(10.dp))
        //Text
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(150.dp)
                .background(brush)
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

@Preview
@Composable
fun PreviewBaseRawListShimmer(){
    LazyRow(content = {
        items(5){
            BaseRawListShimmer()
        }
    })
}