package com.example.feature_profile.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_database_domain.useCase.user.GetStatusRegistrationUseCase
import com.example.core_database_domain.useCase.user.GetUserRoleUseCase
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_network_domain.model.staff.Staff
import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.useCase.admin.GetAdminPlaylistUseCase
import com.example.core_network_domain.useCase.history.GetHistoryMovieUseCase
import com.example.core_network_domain.useCase.history.GetHistorySearchUseCase
import com.example.core_network_domain.useCase.user.GetUserInfoUseCase
import com.example.core_network_domain.useCase.user.PatchUpdatePhotoUserUseCase
import com.example.core_network_domain.useCase.userContent.GetUserCinemaReviewUseCase
import com.example.core_network_domain.useCase.userContent.GetUserFavoriteMovieUseCase
import com.example.core_network_domain.useCase.userContent.GetUserFavoriteStaffUseCase
import com.example.core_network_domain.useCase.userContent.GetUserWatchLaterUseCase
import com.example.core_utils.common.Tag.RETROFIT_TAG
import kotlinx.coroutines.flow.*
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
    private val getAdminPlaylistUseCase: GetAdminPlaylistUseCase,
    getUserRoleUseCase: GetUserRoleUseCase,
    getUserCinemaReviewUseCase: GetUserCinemaReviewUseCase,
    getStatusRegistrationUseCase: GetStatusRegistrationUseCase
):ViewModel() {

    private val _responseUserInfo:MutableStateFlow<User?> =
        MutableStateFlow(null)
    val responseUserInfo = _responseUserInfo.asStateFlow().filterNotNull()

    private val _responseUserFavoriteMovie:MutableStateFlow<Movie?> =
        MutableStateFlow(null)
    val responseUserFavoriteMovie = _responseUserFavoriteMovie.asStateFlow().filterNotNull()

    private val _responseUserFavoriteStaff:MutableStateFlow<Staff?> =
        MutableStateFlow(null)
    val responseUserFavoriteStaff = _responseUserFavoriteStaff.asStateFlow().filterNotNull()

    private val _responseUserWatchLater:MutableStateFlow<Movie?> =
        MutableStateFlow(null)
    val responseUserWatchLater = _responseUserWatchLater.asStateFlow().filterNotNull()

    @ExperimentalSerializationApi
    private val _responseAdminPlaylist:MutableStateFlow<Response<Playlist>> = MutableStateFlow(Response.Loading())
    @ExperimentalSerializationApi
    val responseAdminPlaylist = _responseAdminPlaylist.asStateFlow()

    @ExperimentalSerializationApi
    val responseHistoryMovie = getHistoryMovieUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Eagerly, Response.Loading()
    )

    @ExperimentalSerializationApi
    val responseCinemaReview = getUserCinemaReviewUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Eagerly, Response.Loading()
    )

    @ExperimentalSerializationApi
    val responseHistorySearch = getHistorySearchUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Eagerly, Response.Loading()
    )

    val responseStatusRegistration = getStatusRegistrationUseCase.invoke()

    val responseUserRole = getUserRoleUseCase.invoke()

    fun getUserInfo() = viewModelScope.launch {
        try {
            val response = getUserInfoUseCase.invoke()
            _responseUserInfo.value = response
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }

    @ExperimentalSerializationApi
    fun getAdminPlaylist() = viewModelScope.launch {
        try {
            val response = getAdminPlaylistUseCase.invoke()
            _responseAdminPlaylist.value = response
        } catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }

    fun getUserFavoriteMovie() = viewModelScope.launch {
        try {
            val response = getUserFavoriteMovieUseCase.invoke()
            _responseUserFavoriteMovie.value = response
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }

    fun getUserFavoriteStaff() = viewModelScope.launch {
        try {
            val response = getUserFavoriteStaffUseCase.invoke()
            _responseUserFavoriteStaff.value = response
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }

    fun getUserWatchLater() = viewModelScope.launch {
        try {
            val response = getUserWatchLaterUseCase.invoke()
            _responseUserWatchLater.value = response
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }

    fun patchUpdatePhoto(byteArray: ByteArray) = viewModelScope.launch {
        try {
            patchUpdatePhotoUserUseCase.invoke(byteArray)
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }
}