package com.example.kinopoisk.screen.review

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.review.ReviewDetail
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.getTime
import com.example.core_utils.common.launchWhenStarted
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmMoreNavGraph.FilmMoreScreenRoute
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun ReviewDetailScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    reviewId:Int,
    filmId:Int
) {
    val context = LocalContext.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val reviewViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .reviewViewModel()

    val reviewDetail = remember { mutableStateOf(ReviewDetail()) }

    reviewViewModel.getReviewDetail(id = reviewId)
    reviewViewModel.responseReviewDetail.onEach {
        reviewDetail.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = reviewDetail.value.reviewTitle)
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(FilmMoreScreenRoute.ReviewFilmMore.base(filmId.toString())
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
                                text = getTime(
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