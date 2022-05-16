package com.example.feature_persons.view.sorting

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.state.FilmSortingOrder
import com.example.feature_persons.viewModel.SearchViewModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalMaterialApi
@Composable
internal fun FilmSortingView(
    searchViewModel: SearchViewModel
) {
    var selectedTabIndexOrder by remember { mutableStateOf(searchViewModel.responseOrderFilm.value.ordinal) }
    val sliderPositionRatingFrom by searchViewModel.responseRatingFromFilm
    val sliderPositionRatingTo by searchViewModel.responseRatingToFilm

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        backgroundColor = primaryBackground,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Film Sorting",
                    modifier = Modifier.padding(5.dp),
                    color = secondaryBackground,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }

            if (expandedState){

                Text(
                    text = "Order",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold
                )

                TabRow(
                    selectedTabIndex = selectedTabIndexOrder,
                    backgroundColor = primaryBackground,
                    contentColor = secondaryBackground
                ) {
                    FilmSortingOrder.values().forEach { item ->
                        Tab(
                            selected = selectedTabIndexOrder == item.ordinal,
                            onClick = {
                                searchViewModel.updateOrderFilm(item)
                                selectedTabIndexOrder = item.ordinal
                            },
                            text = { Text(text = item.title) }
                        )
                    }
                }

                Text(
                    text = "Rating from & rating to",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold
                )

                Row {
                    Text(
                        text = "From $sliderPositionRatingFrom",
                        modifier = Modifier.padding(5.dp)
                    )

                    Slider(
                        value = sliderPositionRatingFrom.toFloat(),
                        onValueChange = { searchViewModel.updateRatingFromFilm(it.toInt()) },
                        valueRange = 0f..10f,
                        colors = SliderDefaults.colors(
                            thumbColor = secondaryBackground,
                            activeTrackColor = secondaryBackground,
                        )
                    )
                }

                Row {
                    Text(
                        text = "To $sliderPositionRatingTo",
                        modifier = Modifier.padding(5.dp)
                    )

                    Slider(
                        value = sliderPositionRatingTo.toFloat(),
                        onValueChange = { searchViewModel.updateRatingToFilm(it.toInt()) },
                        valueRange = 0f..10f,
                        colors = SliderDefaults.colors(
                            thumbColor = secondaryBackground,
                            activeTrackColor = secondaryBackground
                        )
                    )
                }
            }
        }
    }
}