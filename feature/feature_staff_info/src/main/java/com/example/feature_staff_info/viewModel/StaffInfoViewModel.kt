package com.example.feature_staff_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_database_domain.useCase.user.GetStatusRegistrationUseCase
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_network_domain.useCase.movie.GetFilmInfoUseCase
import com.example.core_network_domain.useCase.person.GetStaffInfoUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StaffInfoViewModel @Inject constructor(
    private val getStaffInfoUseCase: GetStaffInfoUseCase,
    private val getFilmInfoUseCase: GetFilmInfoUseCase,
    getStatusRegistrationUseCase: GetStatusRegistrationUseCase
):ViewModel() {

    private val _responseStaffInfo:MutableStateFlow<Response<StaffInfo>> = MutableStateFlow(Response.Loading())
    val responseStaffInfo = _responseStaffInfo.asStateFlow()

    val getStatusRegistration = getStatusRegistrationUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Eagerly, false
    )

    fun getStaffInfo(id:Int){
        getStaffInfoUseCase.invoke(id).onEach {
            _responseStaffInfo.value = it
        }.launchIn(viewModelScope)
    }

    fun getFilmInfo(id: Int): Flow<Response<FilmInfo>> {
        return getFilmInfoUseCase(id)
    }
}