package com.example.kinopoisk.screen.filmInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.Budget
import com.example.kinopoisk.api.model.filmInfo.Fact
import com.example.kinopoisk.api.model.filmInfo.Similar
import com.example.kinopoisk.api.model.staff.Staff
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val responseBudget:StateFlow<Budget> = _responseBudget
    private val _responseFact:MutableStateFlow<Fact> = MutableStateFlow(Fact())
    val responseFact:StateFlow<Fact> = _responseFact
    private val _responseStaff:MutableStateFlow<List<Staff>> = MutableStateFlow(listOf())
    val responseStaff:StateFlow<List<Staff>> = _responseStaff
    private val _responseSimilar:MutableStateFlow<Similar> = MutableStateFlow(Similar())
    val responseSimilar:StateFlow<Similar> = _responseSimilar

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
}