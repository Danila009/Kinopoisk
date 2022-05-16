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
import com.example.core_ui.view.MenuItem
import com.example.core_utils.state.RiakAndMortyCharactersStatus
import com.example.feature_persons.viewModel.SearchViewModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalMaterialApi
@ExperimentalSerializationApi
@Composable
fun RickAndMortyCharacterSortingView(
    searchViewModel: SearchViewModel
) {

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
                    text = "Riak and morty Sorting",
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

            if(expandedState){
                MenuItem(
                    list = RiakAndMortyCharactersStatus.values().sorted().map { it.name.uppercase() },
                    value = searchViewModel.responseRiakAndMortyStatusCharacters.value?.title ?: "--",
                    onSelected = {
                        searchViewModel.updateRiakAndMortyCharactersStatus(
                            riakAndMortyCharactersStatus = enumValueOf(it)
                        )
                    }, label = "Characters status"
                )
            }
        }
    }
}