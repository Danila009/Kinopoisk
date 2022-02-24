package com.example.kinopoisk.screen.shop.shopViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.preferenceManager.UserPreferenceRepository
import com.example.kinopoisk.screen.shop.source.ShopPagingShop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val userRepository: ApiUserRepository,
    private val userPreferenceRepository: UserPreferenceRepository
):ViewModel() {
    private val _responseUserRole:MutableStateFlow<String> = MutableStateFlow("")
    val responseUserRole:StateFlow<String> = _responseUserRole.asStateFlow()

    fun getShop(
        ratingMin:Float? = null,
        ratingMax: Float? = null,
        search:String? = ""
    ):Flow<PagingData<Shop>>{
        return Pager(PagingConfig(pageSize = 1)){
            ShopPagingShop(
                userRepository = userRepository,
                ratingMin = ratingMin,
                ratingMax = ratingMax,
                search = search
            )
        }.flow.cachedIn(viewModelScope)
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
}