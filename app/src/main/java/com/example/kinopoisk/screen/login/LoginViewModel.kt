package com.example.kinopoisk.screen.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.ApiUserRepository
import com.example.kinopoisk.api.model.user.Authorization
import com.example.kinopoisk.api.model.user.Registration
import com.example.kinopoisk.navigation.MAIN_ROUTE
import com.example.kinopoisk.utils.Constants.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiUserRepository: ApiUserRepository,
    private val sharedPreferences: SharedPreferences
):ViewModel() {

    fun postAuthorization(authorization: Authorization, navController: NavController){
        viewModelScope.launch {
            try {
                val response = apiUserRepository.postAuthorization(
                    authorization = authorization
                )
                if (response.isSuccessful){
                    sharedPreferences.edit()
                        .putString(TOKEN, response.body()?.access_token)
                        .apply()
                    navController.navigate(MAIN_ROUTE)
                }
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }

    fun postRegistration(registration: Registration, navController: NavController){
        viewModelScope.launch {
            try {
                apiUserRepository.postRegistration(
                    registration = registration
                )
                postAuthorization(
                    Authorization(
                        email = registration.email,
                        password = registration.password
                    ),
                    navController = navController
                )
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }
}