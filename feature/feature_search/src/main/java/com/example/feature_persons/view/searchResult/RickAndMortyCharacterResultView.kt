package com.example.feature_persons.view.searchResult

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.model.rickAndMorty.CharacterItem
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground

@ExperimentalMaterialApi
internal fun LazyListScope.rickAndMortyCharacterResultView(
    characters:LazyPagingItems<CharacterItem>,
    expandedState: MutableState<Boolean>,
    rotationState:Float,
    charactersTotal:Int
) {
    item {
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
                expandedState.value = !expandedState.value
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Characters rick and morty $charactersTotal",
                    modifier = Modifier.padding(5.dp),
                    color = secondaryBackground
                )

                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState.value = !expandedState.value
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        }
    }

    if (expandedState.value){
        items(characters){ item ->
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clickable {

                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = item?.image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(150.dp)
                            .width(100.dp)
                    ) {
                        val state = painter.state
                        if (
                            state is AsyncImagePainter.State.Loading ||
                            state is AsyncImagePainter.State.Error
                        ) {
                            ImageShimmer(
                                imageHeight = 150.dp,
                                imageWidth = 100.dp
                            )
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }

                    Text(
                        text = item?.name ?: "",
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Divider()
            }
        }
        if (
            characters.loadState.refresh is LoadState.Loading
            || characters.loadState.append is LoadState.Loading
        ){
            item {
                FilmListShimmer()
            }
        }
    }
}