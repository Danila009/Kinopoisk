package com.example.kinopoisk.screen.more.view

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kinopoisk.screen.filmInfo.viewState.ImageViewState

@Composable
fun TabView(
    tabIndex:Int,
    onClick:(ImageViewState) -> Unit
) {
    ScrollableTabRow(selectedTabIndex = tabIndex) {
        ImageViewState.values().forEachIndexed { index, imageViewState ->
           Tab(
               selected = tabIndex == index,
               onClick = { onClick(imageViewState) },
               text = { Text(text = imageViewState.name)},
           )
        }
    }
}