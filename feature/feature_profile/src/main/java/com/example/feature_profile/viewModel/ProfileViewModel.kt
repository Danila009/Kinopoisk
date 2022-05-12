package com.example.feature_profile.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_database_domain.useCase.user.GetStatusRegistrationUseCase
import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.staff.Staff
import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.useCase.history.GetHistoryMovieUseCase
import com.example.core_network_domain.useCase.history.GetHistorySearchUseCase
import com.example.core_network_domain.useCase.user.GetUserInfoUseCase
import com.example.core_network_domain.useCase.user.PatchUpdatePhotoUserUseCase
import com.example.core_network_domain.useCase.userContent.GetUserFavoriteMovieUseCase
import com.example.core_network_domain.useCase.userContent.GetUserFavoriteStaffUseCase
import com.example.core_network_domain.useCase.userContent.GetUserWatchLaterUseCase
import com.example.core_utils.common.Tag.RETROFIT_TAG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getHistoryMovieUseCase: GetHistoryMovieUseCase,
    private val getHistorySearchUseCase: GetHistorySearchUseCase,
    private val getUserFavoriteMovieUseCase: GetUserFavoriteMovieUseCase,
    private val getUserFavoriteStaffUseCase: GetUserFavoriteStaffUseCase,
    private val getUserWatchLaterUseCase: GetUserWatchLaterUseCase,
    private val patchUpdatePhotoUserUseCase: PatchUpdatePhotoUserUseCase,
    getStatusRegistrationUseCase: GetStatusRegistrationUseCase
):ViewModel() {

    private val _responseUserInfo:MutableStateFlow<User?> =
        MutableStateFlow(null)
    val responseUserInfo = _responseUserInfo.asStateFlow().filterNotNull()

    @ExperimentalSerializationApi
    private val _responseHistorySearch:MutableStateFlow<HistorySearch?> =
        MutableStateFlow(null)
    @ExperimentalSerializationApi
    val responseHistorySearch = _responseHistorySearch.asStateFlow().filterNotNull()

    @ExperimentalSerializationApi
    private val _responseHistoryMovie:MutableStateFlow<HistoryMovie?> =
        MutableStateFlow(null)
    @ExperimentalSerializationApi
    val responseHistoryMovie = _responseHistoryMovie.asStateFlow().filterNotNull()

    private val _responseUserFavoriteMovie:MutableStateFlow<Movie?> =
        MutableStateFlow(null)
    val responseUserFavoriteMovie = _responseUserFavoriteMovie.asStateFlow().filterNotNull()

    private val _responseUserFavoriteStaff:MutableStateFlow<Staff?> =
        MutableStateFlow(null)
    val responseUserFavoriteStaff = _responseUserFavoriteStaff.asStateFlow().filterNotNull()

    private val _responseUserWatchLater:MutableStateFlow<Movie?> =
        MutableStateFlow(null)
    val responseUserWatchLater = _responseUserWatchLater.asStateFlow().filterNotNull()

    val responseStatusRegistration = getStatusRegistrationUseCase.invoke()

    fun getUserInfo() = viewModelScope.launch {
        val response = getUserInfoUseCase.invoke()
        _responseUserInfo.value = response
    }

    @ExperimentalSerializationApi
    fun getHistoryMovie() = viewModelScope.launch {
        val response = getHistoryMovieUseCase.invoke()
        _responseHistoryMovie.value = response
    }

    @ExperimentalSerializationApi
    fun getHistorySearch() = viewModelScope.launch {
            val response = getHistorySearchUseCase.invoke()
            _responseHistorySearch.value = response
    }

    fun getUserFavoriteMovie() = viewModelScope.launch {
        val response = getUserFavoriteMovieUseCase.invoke()
        _responseUserFavoriteMovie.value = response
    }

    fun getUserFavoriteStaff() = viewModelScope.launch {
        val response = getUserFavoriteStaffUseCase.invoke()
        _responseUserFavoriteStaff.value = response
    }

    fun getUserWatchLater() = viewModelScope.launch {
        val response = getUserWatchLaterUseCase.invoke()
        _responseUserWatchLater.value = response
    }

    fun patchUpdatePhoto(byteArray: ByteArray) = viewModelScope.launch {
        try {
            patchUpdatePhotoUserUseCase.invoke(byteArray)
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }
}