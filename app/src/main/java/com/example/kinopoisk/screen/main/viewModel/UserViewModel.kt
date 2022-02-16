package com.example.kinopoisk.screen.main.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.ApiUserRepository
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.user.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val apiUserRepository: ApiUserRepository
):ViewModel() {

    private val _responseUserInfo: MutableStateFlow<UserInfo> = MutableStateFlow(UserInfo())
    val responseUserInfo: StateFlow<UserInfo> = _responseUserInfo

    fun getUserInfo(){
        viewModelScope.launch {
            try {
                _responseUserInfo.value = apiUserRepository.getUserInfo().body()!!
            }catch (e: Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun postFavoriteFilm(
        filmItem: FilmItem
    ){
        viewModelScope.launch {
            try {
                apiUserRepository.postFavoriteFilm(
                    filmItem = filmItem
                )
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }
}