package com.example.kinopoisk.screen.cinema

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.cinema.Review
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.constants.CinemaScreenRoute
import com.example.kinopoisk.screen.cinema.view.BaseTextField
import com.example.kinopoisk.screen.cinema.viewModel.CinemaViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import kotlinx.coroutines.flow.onEach

@Composable
fun AddReviewCinemaScreen(
    cinemaViewModel: CinemaViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    cinemaId:Int
) {
    val userInfo = remember { mutableStateOf(UserInfo()) }
    val rating = remember { mutableStateOf(5f) }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val cinema = remember { mutableStateOf(Cinema()) }

    cinemaViewModel.getCinema(id = cinemaId)
    cinemaViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope)

    cinemaViewModel.getUserInfo()
    cinemaViewModel.responseUserInfo.onEach {
        userInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = cinema.value.title)
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(CinemaScreenRoute.CinemaInfo.base(
                            cinemaId = cinemaId
                        ))
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
                            .numStars(5)
                            .stepSize(StepSize.HALF)
                            .size(24.dp)
                            .padding(6.dp)
                            .style(RatingBarStyle.Normal)
                    )
                    
                    BaseTextField(
                        label = "Title",
                        value = title
                    )
                    BaseTextField(
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
                        cinemaViewModel.postCinemaReview(
                            reviewCinema = Review(
                                cinemaId = cinemaId,
                                title = title.value,
                                description = description.value,
                                rating = rating.value,
                                userId = userInfo.value.id,
                                userName = userInfo.value.username,
                                date = Converters().getCurrentTime()
                            ), cinemaId = cinemaId
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