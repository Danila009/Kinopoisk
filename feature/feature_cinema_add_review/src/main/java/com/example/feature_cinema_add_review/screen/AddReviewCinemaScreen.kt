package com.example.feature_cinema_add_review.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.cinema.CinemaAddReview
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.BaseTextFieldView
import com.example.core_utils.common.getDate
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.feature_cinema_add_review.viewModel.CinemaAddReviewViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun AddReviewCinemaScreen(
    cinemaAddReviewViewModel:CinemaAddReviewViewModel,
    navController: NavController,
    cinemaId:Int
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val rating = remember { mutableStateOf(5f) }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val cinema = remember { mutableStateOf(Cinema()) }

    cinemaAddReviewViewModel.getCinemaById(id = cinemaId)
    cinemaAddReviewViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope,lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = cinema.value.title)
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = primaryBackground
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    RatingBar(
                        value = rating.value,
                        onValueChange = {rating.value = it},
                        onRatingChanged = {},
                        config = RatingBarConfig()
                            .activeColor(Color.Yellow)
                            .inactiveColor(Color.LightGray)
                            .numStars(10)
                            .stepSize(StepSize.HALF)
                            .size(24.dp)
                            .padding(6.dp)
                            .style(RatingBarStyle.Normal)
                    )

                    BaseTextFieldView(
                        label = "Title",
                        value = title
                    )

                    BaseTextFieldView(
                        label = "Description",
                        value = description
                    )

                    Button(
                        modifier = Modifier
                            .padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = secondaryBackground
                        ), shape = AbsoluteRoundedCornerShape(15.dp),
                        onClick = {
                        cinemaAddReviewViewModel.postReview(
                            id = cinemaId,
                            review = CinemaAddReview(
                                title = title.value,
                                description = description.value,
                                rating = rating.value,
                                date = getDate()
                            )
                        )
                        navController.navigate(CinemaScreenRoute.CinemaInfo.base(
                            cinemaId = cinemaId
                        ))
                    }) {
                        Text(text = "Add review")
                    }
                }
            }
        }
    )
}