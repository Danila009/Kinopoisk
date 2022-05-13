package com.example.feature_cinema_map.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.feature_cinema_map.viewModel.CinemaMapViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CinemaMapScreen(
    navController: NavController,
    cinemaViewModel:CinemaMapViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val checkNavMap = remember { mutableStateOf(false) }
    val cinema = remember { mutableStateOf(listOf<Cinema>()) }

    cinemaViewModel.getCinema()
    cinemaViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            repeat(cinema.value.size){

                if (checkNavMap.value){
                    LaunchedEffect(key1 = Unit, block ={
                        navController.navigate(
                            CinemaScreenRoute.CinemaInfo.base(
                                cinemaId = cinema.value[it].id
                            )
                        )
                    })
                }

                Marker(
                    position = LatLng(
                    cinema.value[it].latitude,
                    cinema.value[it].longitude
                ), title = "${cinema.value[it].title} ${cinema.value[it].adress}",
                    onInfoWindowClick = {
                        checkNavMap.value = true
                    }
                )
            }
        }
    )
}