package com.example.kinopoisk.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.premiere.Premiere
import com.example.kinopoisk.api.model.premiere.ReleaseItem
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.FilmPagingSource
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.ReleasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {
    private val _responsePremiere:MutableStateFlow<Premiere> = MutableStateFlow(Premiere())
    val responsePremiere:StateFlow<Premiere> = _responsePremiere.asStateFlow()

    fun getFilm(
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

    fun getPremiere(year:Int,month:String){
        viewModelScope.launch {
            try {
                _responsePremiere.value = apiRepository.getPremiere(
                    year = year,
                    month = month
                ).body()!!
            }catch (e: Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getRelease(
        year: Int,
        month: String
    ):Flow<PagingData<ReleaseItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            ReleasePagingSource(
                apiRepository = apiRepository,
                year = year,
                mont = month
            )
        }.flow.cachedIn(viewModelScope)
    }
}
