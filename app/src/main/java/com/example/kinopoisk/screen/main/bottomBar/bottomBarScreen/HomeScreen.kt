package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.premiere.Premiere
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.homeView.*
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.viewState.UserRole
import com.example.kinopoisk.utils.launchWhenStarted
import com.example.kinopoisk.utils.viewState.ViewStatePremiere
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController:NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    val userRole = remember { mutableStateOf(UserRole.BaseUser.name) }
    val checkNavMap = remember { mutableStateOf(false) }
    val premiere = remember { mutableStateOf(Premiere()) }
    val shop = remember { mutableStateOf(listOf<Shop>()) }
    val filmListAdmin = remember { mutableStateOf(listOf<AdminFilmList>()) }
    val cinema = remember { mutableStateOf(listOf<Cinema>()) }

    val release = mainViewModel.getRelease(
        year = Converters().getDatePremiere(ViewStatePremiere.YEAR).toInt(),
        month = Converters().getDatePremiere(ViewStatePremiere.MONTH)
    ).collectAsLazyPagingItems()

    mainViewModel.getPremiere(
        year = Converters().getDatePremiere(ViewStatePremiere.YEAR).toInt(),
        month = Converters().getDatePremiere(ViewStatePremiere.MONTH)
    )

    mainViewModel.responsePremiere.onEach {
        premiere.value = it
    }.launchWhenStarted(lifecycleScope)

    mainViewModel.getShop()
    mainViewModel.responseShop.onEach {
        shop.value = it
    }.launchWhenStarted(lifecycleScope)

    mainViewModel.getCinema()
    mainViewModel.responseCinema.onEach {
        cinema.value = it
    }.launchWhenStarted(lifecycleScope)

    mainViewModel.readUserRole()
    mainViewModel.responseUserRole.onEach {
        userRole.value = it
    }.launchWhenStarted(lifecycleScope)

    mainViewModel.getFilmList()
    mainViewModel.responseFilmList.onEach {
        filmListAdmin.value = it
    }.launchWhenStarted(lifecycleScope)

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

                if (shop.value.isNotEmpty()){
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

                TopView(
                    navController = navController,
                    userRole = userRole.value,
                    adminListFilmTop = filmListAdmin.value
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                )
            }
        })
    }
}