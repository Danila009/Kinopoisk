package com.example.kinopoisk.screen.setting.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.repository.ApiUserRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val apiUserRepository: ApiUserRepository
):ViewModel() {

    fun deleteHistoryAll(){
        viewModelScope.launch {
            try {
                apiUserRepository.deleteHistoryAll()
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }
}