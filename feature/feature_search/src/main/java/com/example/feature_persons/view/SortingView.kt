package com.example.feature_persons.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.feature_persons.view.sorting.CinemaSortingView
import com.example.feature_persons.view.sorting.FilmSortingView
import com.example.feature_persons.view.sorting.RickAndMortyCharacterSortingView
import com.example.feature_persons.viewModel.SearchViewModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalMaterialApi
@Composable
fun SortingView(
    searchViewModel: SearchViewModel
) {
    Column {
        FilmSortingView(
            searchViewModel = searchViewModel
        )

        CinemaSortingView(
            searchViewModel = searchViewModel
        )

        RickAndMortyCharacterSortingView(
            searchViewModel = searchViewModel
        )
    }
}