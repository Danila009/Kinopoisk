package com.example.feature_comics.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.core_utils.state.ComicsState
import com.example.feature_comics.view.MarvelComicsView
import com.example.feature_comics.viewModel.ComicsViewModel

@ExperimentalFoundationApi
@Composable
fun ComicsScreen(
    navController: NavController,
    comicsViewModel: ComicsViewModel,
    comicsState: ComicsState
) = when(comicsState){
    ComicsState.MARVEL -> MarvelComicsView(
        navController = navController,
        comicsViewModel = comicsViewModel
    )
}