package com.example.kinopoisk.screen.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.review.ReviewDetail
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@Composable
fun ReviewDetailScreen(
    reviewViewModel: ReviewViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    reviewId:Int,
    filmId:Int
) {
    val reviewDetail = remember { mutableStateOf(ReviewDetail()) }

    reviewViewModel.getReviewDetail(id = reviewId)
    reviewViewModel.responseReviewDetail.onEach {
        reviewDetail.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = reviewDetail.value.reviewTitle)
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.ReviewMore.base(filmId.toString())
                        ) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            LazyColumn(content = {
                item {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = primaryBackground
                    ) {
                        Column {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = reviewDetail.value.reviewAutor,
                                    modifier = Modifier
                                        .padding(5.dp),
                                    color = secondaryBackground,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )

                                Row {
                                    Text(
                                        text = "+ ${reviewDetail.value.userPositiveRating}",
                                        color = Color.Green,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "- ${reviewDetail.value.userNegativeRating}",
                                        color = Color.Red,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                            }

                            Text(
                                text = reviewDetail.value.reviewDescription,
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                            Text(
                                text = Converters().getTime(
                                    reviewDetail.value.reviewData
                                ),
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.End,
                                color = secondaryBackground,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            })
        }
    )
}