package com.example.kinopoisk.screen.filmTop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.topFilm.TopItem
import com.example.kinopoisk.screen.filmTop.source.TopPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilmTopViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {
    fun getTop(
        type: String
    ): Flow<PagingData<TopItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            TopPagingSource(
                apiRepository = apiRepository,
                type = type
            )
        }.flow.cachedIn(viewModelScope)
    }
}