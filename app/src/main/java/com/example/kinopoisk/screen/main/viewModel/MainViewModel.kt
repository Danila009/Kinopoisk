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
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.person.PersonItem
import com.example.kinopoisk.api.model.premiere.Premiere
import com.example.kinopoisk.api.model.premiere.ReleaseItem
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.preferenceManager.UserPreferenceRepository
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.FilmPagingSource
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.PersonPagingSource
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source.ReleasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
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

    fun getFilm(
        order:String = "RATING",
        type:String = "ALL",
        ratingFrom:Int = 0,
        ratingTo:Int = 10,
        yearFrom:Int = 1000,
        yearTo:Int = 3000,
        keyword:String = ""
    ):Flow<PagingData<FilmItem>>  {
         return  Pager(PagingConfig(pageSize = 1)){
            FilmPagingSource(
                order = order,
                type = type,
                ratingFrom = ratingFrom,
                ratingTo = ratingTo,
                yearFrom = yearFrom,
                yearTo = yearTo,
                keyword = keyword,
                apiRepository = apiRepository
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getPremiere(year:Int,month:String){
        viewModelScope.launch {
            try {
                _responsePremiere.value = apiRepository.getPremiere(
                    year = year,
                    month = month
                ).body()!!
            }catch (e: Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

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
}
