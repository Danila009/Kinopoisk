package com.example.kinopoisk.screen.cinema

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.screen.cinema.viewModel.CinemaViewModel

@Composable
fun AddReviewCinemaScreen(
    cinemaViewModel: CinemaViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    cinemaId:Int
) {


}