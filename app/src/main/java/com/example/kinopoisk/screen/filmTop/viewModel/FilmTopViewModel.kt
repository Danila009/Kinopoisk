package com.example.kinopoisk.screen.filmTop.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.model.topFilm.TopItem
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.screen.filmTop.source.TopPagingSource
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.FilmPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FilmTopViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val apiUserRepository: ApiUserRepository
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

    fun postFilmList(filmList: AdminFilmList){
        viewModelScope.launch {
            try {
                apiUserRepository.postFilmList(filmList = filmList)
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }

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
    ):Flow<PagingData<FilmItem>>  {
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
                apiRepository = apiRepository
            )
        }.flow.cachedIn(viewModelScope)
    }
}