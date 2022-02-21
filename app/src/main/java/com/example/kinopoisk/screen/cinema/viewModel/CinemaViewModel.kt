package com.example.kinopoisk.screen.cinema.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.cinema.Review
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.api.repository.ApiUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(
    private val apiUserRepository: ApiUserRepository
):ViewModel() {
    private val _responseCinemas:MutableStateFlow<List<Cinema>> = MutableStateFlow(listOf())
    val responseCinemas:StateFlow<List<Cinema>> = _responseCinemas.asStateFlow()
    private val _responseCinema:MutableStateFlow<Cinema> = MutableStateFlow(Cinema())
    val responseCinema:StateFlow<Cinema> = _responseCinema.asStateFlow()
    private val _responseUserInfo:MutableStateFlow<UserInfo> = MutableStateFlow(UserInfo())
    val responseUserInfo:StateFlow<UserInfo> = _responseUserInfo.asStateFlow()

    fun getCinemas(){
        viewModelScope.launch {
            try {
                _responseCinemas.value = apiUserRepository.getCinemas().body()!!
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }

    fun getCinema(
        id:Int
    ){
        viewModelScope.launch {
            try {
                _responseCinema.value = apiUserRepository.getCinema(
                    id = id
                ).body()!!
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }

    fun postCinemaReview(
        reviewCinema:Review,
        cinemaId:Int
    ){
        viewModelScope.launch {
            try {
                apiUserRepository.postCinemaReview(
                    reviewCinema = reviewCinema,
                    cinemaId = cinemaId
                )
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }

    fun getUserInfo(){
        viewModelScope.launch {
            try {
                _responseUserInfo.value = apiUserRepository.getUserInfo().body()!!
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }
}