package com.example.feature_cinema_info.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_database_domain.useCase.user.GetStatusRegistrationUseCase
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.*
import com.example.core_network_domain.useCase.cinema.*
import com.example.core_utils.common.Tag.RETROFIT_TAG
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CinemaInfoViewModel @Inject constructor(
    private val getCinemaByIdUseCase: GetCinemaByIdUseCase,
    private val getCinemaPhotosUseCase: GetCinemaPhotosUseCase,
    private val getCinemaPhoneUseCase: GetCinemaPhoneUseCase,
    private val getCinemaScheduleUseCase: GetCinemaScheduleUseCase,
    private val getCinemaReviewUseCase: GetCinemaReviewUseCase,
    getStatusRegistrationUseCase: GetStatusRegistrationUseCase
):ViewModel() {

    private val _responseCinema:MutableStateFlow<Response<Cinema>> =
        MutableStateFlow(Response.Loading())
    val responseCinema = _responseCinema.asStateFlow().filterNotNull()

    private val _responseCinemaPhotos = MutableStateFlow(emptyList<Photo>())
    val responseCinemaPhotos = _responseCinemaPhotos.asStateFlow().filterNotNull()

    private val _responseCinemaPhone = MutableStateFlow(emptyList<Phone>())
    val responseCinemaPhone = _responseCinemaPhone.asStateFlow().filterNotNull()

    private val _responseCinemaSchedule = MutableStateFlow(listOf<Schedule>())
    val responseCinemaSchedule = _responseCinemaSchedule.asStateFlow().filterNotNull()

    private val _responseCinemaReview:MutableStateFlow<Response<Review>> =
        MutableStateFlow(Response.Loading())
    val responseCinemaReview = _responseCinemaReview.asStateFlow().filterNotNull()

    val responseStatusRegistration = getStatusRegistrationUseCase.invoke()

    fun getCinemaById(id:Int){
        getCinemaByIdUseCase.invoke(id).onEach {
            _responseCinema.value = it
        }.launchIn(viewModelScope)
    }

    fun getCinemaPhotos(id: Int){
        viewModelScope.launch {
            try {
                val response = getCinemaPhotosUseCase.invoke(id)
                _responseCinemaPhotos.value = response
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
        }
    }

    fun getCinemaPhone(id: Int){
        viewModelScope.launch {
            try {
                val response = getCinemaPhoneUseCase.invoke(id)
                _responseCinemaPhone.value = response
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
        }
    }

    fun getCinemaSchedule(id: Int){
        viewModelScope.launch {
            try {
                val response = getCinemaScheduleUseCase.invoke(id)
                _responseCinemaSchedule.value = response
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
        }
    }

    fun getCinemaReview(
        id: Int,
        search: String,
        startDate:String?,
        endDate:String?,
        startRating:Float?,
        endRating:Float?
    ){
        getCinemaReviewUseCase.invoke(
            id, search, startDate, endDate, startRating, endRating
        ).onEach {
            _responseCinemaReview.value = it
        }.launchIn(viewModelScope)
    }
}