package com.example.kinopoisk.screen.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.api.model.user.Authorization
import com.example.kinopoisk.api.model.user.Registration
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.preferenceManager.UserPreferenceRepository
import com.example.kinopoisk.utils.Constants.TOKEN_SHARED
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiUserRepository: ApiUserRepository,
    private val sharedPreferences: SharedPreferences,
    private val userPreferenceRepository: UserPreferenceRepository
):ViewModel() {

    fun postAuthorization(authorization: Authorization, navController: NavController){
        viewModelScope.launch {
            try {
                val response = apiUserRepository.postAuthorization(
                    authorization = authorization
                )
                if (response.isSuccessful){
                    sharedPreferences.edit()
                        .putString(TOKEN_SHARED, response.body()?.access_token)
                        .apply()
                    saveStatusRegistration(userRegistration = true)
                    savaUserRole(userRole = response.body()!!.role)
                    navController.navigate(Screen.Main.route)
                }
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
                saveStatusRegistration(userRegistration = false)
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

    private fun saveStatusRegistration(userRegistration:Boolean){
        viewModelScope.launch {
            try {
                userPreferenceRepository.saveStatusRegistration(userRegistration)
            }catch (e:Exception){
                Log.d("DateStore:",e.message.toString())
            }
        }
    }

    private fun savaUserRole(userRole:String){
        viewModelScope.launch {
            try {
                userPreferenceRepository.savaUserRole(userRole)
            }catch (e:Exception){
                Log.d("DateStore:",e.message.toString())
            }
        }
    }

    fun putUserPassword(
        email:String,
        password:String,
    ){
        viewModelScope.launch {
            try {
                apiUserRepository.putUserPassword(
                    email = email,
                    password = password
                )
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }
}