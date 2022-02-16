package com.example.kinopoisk.screen.staffInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.model.staff.StaffInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class StaffInfoViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {

    private val _responseStaffInfo:MutableStateFlow<StaffInfo> = MutableStateFlow(StaffInfo())
    val responseStaffInfo:StateFlow<StaffInfo> = _responseStaffInfo

    fun getStaffInfo(id:Int){
        viewModelScope.launch {
            try {
                _responseStaffInfo.value = apiRepository.getStaffInfo(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:", e.message.toString())
            }
        }
    }


}