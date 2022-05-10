package com.example.core_ui.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ImageShimmer(
    imageHeight: Dp = 180.dp,
    imageWidth: Dp = 140.dp
) {
    val brush = baseAnimationShimmer()

    Spacer(
        modifier = Modifier
            .size(
                height = imageHeight,
                width = imageWidth
            )
            .clip(AbsoluteRoundedCornerShape(8.dp))
            .background(brush)
    )
}