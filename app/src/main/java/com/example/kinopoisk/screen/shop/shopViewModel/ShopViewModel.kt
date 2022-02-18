package com.example.kinopoisk.screen.shop.shopViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.screen.shop.source.ShopPagingShop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val userRepository: ApiUserRepository
):ViewModel() {
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
}