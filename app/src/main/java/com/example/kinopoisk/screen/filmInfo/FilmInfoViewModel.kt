package com.example.kinopoisk.screen.filmInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.*
import com.example.kinopoisk.api.model.review.ReviewItem
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.screen.filmInfo.source.ImagePagingSource
import com.example.kinopoisk.screen.filmInfo.source.ReviewPagingSource
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.ReleasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FilmInfoViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {
    private val _responseFilmInfo:MutableStateFlow<FilmInfo> = MutableStateFlow(FilmInfo())
    val responseFilmInfo:StateFlow<FilmInfo> = _responseFilmInfo.asStateFlow()
    private val _responseBudget:MutableStateFlow<Budget> = MutableStateFlow(Budget())
    val responseBudget:StateFlow<Budget> = _responseBudget.asStateFlow()
    private val _responseFact:MutableStateFlow<Fact> = MutableStateFlow(Fact())
    val responseFact:StateFlow<Fact> = _responseFact.asStateFlow()
    private val _responseStaff:MutableStateFlow<List<Staff>> = MutableStateFlow(listOf())
    val responseStaff:StateFlow<List<Staff>> = _responseStaff.asStateFlow()
    private val _responseSimilar:MutableStateFlow<Similar> = MutableStateFlow(Similar())
    val responseSimilar:StateFlow<Similar> = _responseSimilar.asStateFlow()
    private val _responseSequelAndPrequel:MutableStateFlow<List<SequelAndPrequel>> = MutableStateFlow(listOf())
    val responseSequelAndPrequel:StateFlow<List<SequelAndPrequel>> = _responseSequelAndPrequel.asStateFlow()
    private val _responseSeason:MutableStateFlow<Season> = MutableStateFlow(Season())
    val responseSeason:StateFlow<Season> = _responseSeason.asStateFlow()

    fun getFilmInfo(id:Int){
        viewModelScope.launch {
            try {
                _responseFilmInfo.value = apiRepository.getFilmInfo(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getBudget(id: Int){
        viewModelScope.launch {
            try {
                _responseBudget.value = apiRepository.getBudget(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getFact(id: Int){
        viewModelScope.launch {
            try {
                _responseFact.value = apiRepository.getFact(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getStaff(id: Int){
        viewModelScope.launch {
            try {
                _responseStaff.value = apiRepository.getStaff(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSimilar(id: Int){
        viewModelScope.launch {
            try {
                _responseSimilar.value = apiRepository.getSimilar(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSequelAndPrequel(id: Int){
        viewModelScope.launch {
            try {
                _responseSequelAndPrequel.value = apiRepository.getSequelAndPrequel(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSeason(id: Int){
        viewModelScope.launch {
            try {
                _responseSeason.value = apiRepository.getSeason(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getImage(
        id: Int,
        type:String
    ): Flow<PagingData<ImageItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            ImagePagingSource(
                apiRepository = apiRepository,
                id = id,
                type = type
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getReview(
        id: Int
    ):Flow<PagingData<ReviewItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            ReviewPagingSource(
                apiRepository = apiRepository,
                id = id
            )
        }.flow.cachedIn(viewModelScope)
    }
}