package com.example.feature_cinema_info.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.BaseConstants.IMAGE_NO_PHOTO_URL
import com.example.core_utils.common.rating

@ExperimentalMaterialApi
@Composable
fun ReviewView(
    review: ReviewItem
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    SubcomposeAsyncImage(
                        model = if (review.user.photo != null) review.user.photo else "" +
                                IMAGE_NO_PHOTO_URL,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(80.dp)
                            .clip(CircleShape)
                    ) {
                        val state = painter.state
                        if (
                            state is AsyncImagePainter.State.Loading ||
                            state is AsyncImagePainter.State.Error
                        ) {
                            ImageShimmer(
                                modifier = Modifier
                                    .clip(CircleShape)
                            )
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }


                Text(
                    text = review.user.username,
                    modifier = Modifier.padding(5.dp),
                    color = secondaryBackground,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }

            Row {
                Text(
                    text = review.title,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = review.rating.toString(),
                    modifier = Modifier.padding(5.dp),
                    color = rating(rating = review.rating),
                    fontWeight = FontWeight.Bold
                )
            }

            if (expandedState){
                Text(
                    text = review.description,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = review.date,
                    modifier = Modifier.padding(5.dp)
                )
            }

            Divider()
        }
    }
}