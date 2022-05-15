package com.example.feature_films.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.model.movie.FilmItem
import com.example.core_network_domain.useCase.movie.GetFilmUseCase
import com.example.core_network_domain.source.FilmPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmsViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase
):ViewModel() {


    fun getFilm(
        genres:List<Int> = listOf(),
        countries:List<Int> = listOf(),
        order:String = "RATING",
        type:String = "ALL",
        ratingFrom:Int = 0,
        ratingTo:Int = 10,
        yearFrom:Int = 1000,
        yearTo:Int = 3000,
        keyword:String = ""
    ): Flow<PagingData<FilmItem>> {
        return  Pager(PagingConfig(pageSize = 1)){
            FilmPagingSource(
                genres = genres,
                countries = countries,
                order = order,
                type = type,
                ratingFrom = ratingFrom,
                ratingTo = ratingTo,
                yearFrom = yearFrom,
                yearTo = yearTo,
                keyword = keyword,
                getFilmUseCase = getFilmUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }
}