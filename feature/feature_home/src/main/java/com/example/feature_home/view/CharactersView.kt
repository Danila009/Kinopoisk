package com.example.feature_home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.state.CharacterState

@Composable
internal fun CharactersView(
    navController: NavController
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Characters:",
            modifier = Modifier.padding(5.dp),
            color = secondaryBackground,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {},
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Все ->",
                color = secondaryBackground
            )
        }
    }

    LazyRow(content = {
        item {
            CharacterState.values().forEach { item ->
                SubcomposeAsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(133.dp)
                        .width(204.dp)
                        .clickable {
                            navController.navigate(item.nav)
                        }
                ) {
                    val state = painter.state
                    if (
                        state is AsyncImagePainter.State.Loading ||
                        state is AsyncImagePainter.State.Error
                    ) {
                        ImageShimmer(
                            imageHeight = 133.dp,
                            imageWidth = 204.dp
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
        }
    })
}
