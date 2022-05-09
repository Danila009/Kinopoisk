package com.example.feature_home.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core_database_domain.common.UserRole
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_network_domain.model.shop.Shop
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_utils.common.getDatePremiere
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.state.StatePremiere
import com.example.feature_home.view.*
import com.example.feature_home.viewModel.HomeViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Composable
fun HomeScreen(
    navController:NavController,
    homeViewModel: HomeViewModel
) {

    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val userRole = remember { mutableStateOf(UserRole.BaseUser) }
    val checkNavMap = remember { mutableStateOf(false) }
    val premiere = remember { mutableStateOf(Premiere()) }
    val shop = remember { mutableStateOf(Shop()) }
    val filmListAdmin = remember { mutableStateOf(listOf<Playlist>()) }
    val cinema = remember { mutableStateOf(listOf<Cinema>()) }
    val comicsMarvel = remember { mutableStateOf(ComicsMarvel()) }

    val release = homeViewModel.getRelease(
        year = getDatePremiere(StatePremiere.YEAR).toInt(),
        month = getDatePremiere(StatePremiere.MONTH)
    ).collectAsLazyPagingItems()

    homeViewModel.getPremiere(
        year = getDatePremiere(StatePremiere.YEAR).toInt(),
        month = getDatePremiere(StatePremiere.MONTH)
    )

    homeViewModel.responsePremiere.onEach {
        premiere.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    homeViewModel.getShop()
    homeViewModel.responseShop.onEach {
        shop.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    homeViewModel.getCinema()
    homeViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    homeViewModel.getUserRole.onEach {
        userRole.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    homeViewModel.getPlaylist()
    homeViewModel.responsePlaylist.onEach {
        filmListAdmin.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    homeViewModel.getComicsMarvel()
    homeViewModel.responseComicsMarvel.onEach {
        it.data?.let { comics ->
            comicsMarvel.value = comics
        }
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = primaryBackground
    ) {
        LazyColumn(content = {
            item {

                if (premiere.value.items.isNotEmpty()){
                    PremiereView(
                        navController = navController,
                        premiere = premiere.value
                    )
                }

                if (release.itemSnapshotList.isNotEmpty()){
                    ReleaseView(
                        navController = navController,
                        release = release
                    )
                }

                if (shop.value.catecory.isNotEmpty()){
                    ShopView(
                        navController = navController,
                        shop = shop.value
                    )
                }

                if (cinema.value.isNotEmpty()){
                    MapView(
                        navController = navController,
                        cinema = cinema.value,
                        checkNavMap = checkNavMap
                    )
                }

                PlaylistView(
                    navController = navController,
                    userRole = userRole.value,
                    playlist = filmListAdmin.value
                )

                ComicsView(
                    comicsMarvel = comicsMarvel.value,
                    navController = navController
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                )
            }
        })
    }
}