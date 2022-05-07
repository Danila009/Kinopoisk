package com.example.kinopoisk.screen.main.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.filmInfo.filter.Filter
import com.example.core_network_domain.model.person.PersonItem
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.ReleaseItem
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.PhotoUser
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.preferenceManager.UserPreferenceRepository
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.PersonPagingSource
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.ReleasePagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val apiUserRepository: ApiUserRepository,
    private val userPreferenceRepository: UserPreferenceRepository
):ViewModel() {
    private val _responsePremiere:MutableStateFlow<Premiere> = MutableStateFlow(Premiere())
    val responsePremiere:StateFlow<Premiere> = _responsePremiere.asStateFlow()
    private val _responseUserInfo: MutableStateFlow<UserInfo> = MutableStateFlow(UserInfo())
    val responseUserInfo: StateFlow<UserInfo> = _responseUserInfo.asStateFlow()
    private val _responseStatusRegistration:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val responseStatusRegistration:StateFlow<Boolean> = _responseStatusRegistration.asStateFlow()
    private val _responseShop:MutableStateFlow<List<Shop>> = MutableStateFlow(listOf())
    val responseShop:StateFlow<List<Shop>> = _responseShop.asStateFlow()
    private val _responseCinema:MutableStateFlow<List<Cinema>> = MutableStateFlow(listOf())
    val responseCinema:StateFlow<List<Cinema>> = _responseCinema.asStateFlow()
    private val _responseFilter:MutableStateFlow<Filter> = MutableStateFlow(Filter())
    val responseFilter:StateFlow<Filter> = _responseFilter.asStateFlow()
    private val _responseUserRole:MutableStateFlow<String> = MutableStateFlow("")
    val responseUserRole:StateFlow<String> = _responseUserRole.asStateFlow()
    private val _responseFilmList:MutableStateFlow<List<AdminFilmList>> = MutableStateFlow(listOf())
    val responseFilmList:StateFlow<List<AdminFilmList>> = _responseFilmList.asStateFlow()


    fun getRelease(
        year: Int,
        month: String
    ):Flow<PagingData<ReleaseItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            ReleasePagingSource(
                apiRepository = apiRepository,
                year = year,
                mont = month
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getSearchPerson(
        name:String
    ):Flow<PagingData<PersonItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            PersonPagingSource(
                apiRepository = apiRepository,
                name = name
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getUserInfo(){
        viewModelScope.launch {
            try {
                _responseUserInfo.value = apiUserRepository.getUserInfo().body()!!
            }catch (e: Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun readStatusRegistration(){
        viewModelScope.launch {
            try {
                userPreferenceRepository.readStatusRegistration().collect {
                    _responseStatusRegistration.value = it
                }
            }catch (e:Exception){
                Log.d("DateStore:",e.message.toString())
            }
        }
    }

    fun getShop(
        ratingMin:Float? = 1f,
        ratingMax: Float? = 10f,
        search:String? = ""
    ){
        viewModelScope.launch {
            try {
                _responseShop.value = apiUserRepository.getShop(
                    ratingMin = ratingMin,
                    ratingMax = ratingMax,
                    search = search
                ).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getCinema(){
        viewModelScope.launch {
            try {
                _responseCinema.value = apiUserRepository.getCinemas().body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun putUserPhoto(
        photo: PhotoUser
    ){
        viewModelScope.launch {
            try {
                apiUserRepository.putUserPhoto(
                    photo = photo
                )
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getFilter(){
        viewModelScope.launch {
            try {
                _responseFilter.value = apiRepository.getFilter().body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun readUserRole(){
        viewModelScope.launch {
            try {
                userPreferenceRepository.readUserRole().collect {
                    _responseUserRole.value = it
                }
            }catch (e:Exception){
                Log.d("DateStore:",e.message.toString())
            }
        }
    }

    fun getFilmList(){
        viewModelScope.launch {
            try {
                _responseFilmList.value = apiUserRepository.getFilmList().body()!!
            }catch (e:Exception){
                Log.d("DateStore:",e.message.toString())
            }
        }
    }
}
