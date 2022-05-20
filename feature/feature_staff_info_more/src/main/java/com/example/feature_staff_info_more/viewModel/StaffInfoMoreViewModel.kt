package com.example.feature_staff_info_more.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_network_domain.useCase.person.GetStaffInfoUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StaffInfoMoreViewModel @Inject constructor(
    private val getStaffInfoUseCase: GetStaffInfoUseCase
):ViewModel() {

    private val _responseStaffInfo:MutableStateFlow<Response<StaffInfo>> = MutableStateFlow(Response.Loading())
    val responseStaffInfo = _responseStaffInfo.asStateFlow()

    fun getStaffInfo(id:Int){
        getStaffInfoUseCase.invoke(id).onEach {
            _responseStaffInfo.value = it
        }.launchIn(viewModelScope)
    }

    fun getStaffInfoReturn(id: Int):Flow<Response<StaffInfo>>{
        return getStaffInfoUseCase(id)
    }
}