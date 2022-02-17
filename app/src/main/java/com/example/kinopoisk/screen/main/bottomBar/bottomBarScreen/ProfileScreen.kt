package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.ProfileView
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.TokenNullLoginView
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    val checkRegistration:MutableState<Boolean> = remember { mutableStateOf(false) }

    mainViewModel.readStatusRegistration()
    mainViewModel.responseStatusRegistration.onEach {
        checkRegistration.value = it
    }.launchWhenStarted(lifecycleScope)

    if (checkRegistration.value){
        ProfileView(
            lifecycleScope = lifecycleScope,
            navController = navController
        )
    }else{
        TokenNullLoginView(
            navController = navController
        )
    }
}