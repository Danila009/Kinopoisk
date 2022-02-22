package com.example.kinopoisk.screen.staffInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.model.staff.StaffInfo
import com.example.kinopoisk.api.model.user.StaffFavorite
import com.example.kinopoisk.api.repository.ApiUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class StaffInfoViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val apiUserRepository: ApiUserRepository
):ViewModel() {
    private val _responseStaffInfo:MutableStateFlow<StaffInfo> = MutableStateFlow(StaffInfo())
    val responseStaffInfo:StateFlow<StaffInfo> = _responseStaffInfo.asStateFlow()
    private val _responseStaffFavoriteCheck:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val responseStaffFavoriteCheck:StateFlow<Boolean> = _responseStaffFavoriteCheck.asStateFlow()

    fun getStaffInfo(id:Int){
        viewModelScope.launch {
            try {
                _responseStaffInfo.value = apiRepository.getStaffInfo(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:", e.message.toString())
            }
        }
    }

    fun postStaffFavorite(staffFavorite: StaffFavorite){
        viewModelScope.launch {
            try {
                apiUserRepository.postStaffFavorite(
                    staffFavorite = staffFavorite
                )
            }catch (e:Exception){
                Log.d("Retrofit:", e.message.toString())
            }
        }
    }

    fun getStaffFavoriteCheck(staffId:Int){
        viewModelScope.launch {
            try {
                _responseStaffFavoriteCheck.value = apiUserRepository.getStaffFavoriteCheck(staffId = staffId).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:", e.message.toString())
            }
        }
    }
}