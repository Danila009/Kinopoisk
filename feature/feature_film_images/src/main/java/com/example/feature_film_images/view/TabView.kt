package com.example.feature_film_images.view

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.core_utils.state.ImageState

@Composable
internal fun TabImageView(
    tabIndex:Int,
    onClick:(ImageState) -> Unit
) {
    ScrollableTabRow(selectedTabIndex = tabIndex) {
        ImageState.values().forEachIndexed { index, imageViewState ->
            Tab(
                selected = tabIndex == index,
                onClick = { onClick(imageViewState) },
                text = { Text(text = imageViewState.name)},
            )
        }
    }
}