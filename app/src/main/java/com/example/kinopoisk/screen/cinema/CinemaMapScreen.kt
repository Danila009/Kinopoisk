package com.example.kinopoisk.screen.cinema

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.screen.cinema.viewModel.CinemaViewModel
import com.example.kinopoisk.utils.launchWhenStarted
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import kotlinx.coroutines.flow.onEach

@Composable
fun CinemaMapScreen(
    cinemaViewModel: CinemaViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    val cinema = remember { mutableStateOf(listOf<Cinema>()) }

    cinemaViewModel.getCinemas()
    cinemaViewModel.responseCinemas.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope)

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            repeat(cinema.value.size){
                Marker(
                    position = LatLng(
                    cinema.value[it].mapOne,
                    cinema.value[it].mapTwo
                ), title = cinema.value[it].title)
            }
        }
    )
}