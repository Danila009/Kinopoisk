package com.example.feature_home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_database_domain.useCase.user.GetUserRoleUseCase
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.ReleaseItem
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_network_domain.model.shop.Shop
import com.example.core_network_domain.useCase.cinema.GetCinemaUseCase
import com.example.core_network_domain.useCase.movie.GetPremiereUseCase
import com.example.core_network_domain.useCase.movie.GetReleaseUseCase
import com.example.core_network_domain.useCase.playlist.GetPlaylistUseCase
import com.example.core_network_domain.useCase.shop.GetShopUseCase
import com.example.feature_home.source.ReleasePagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class HomeViewModel @Inject constructor(
    private val getPremiereUseCase: GetPremiereUseCase,
    private val getShopUseCase: GetShopUseCase,
    private val getCinemaUseCase: GetCinemaUseCase,
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val getReleaseUseCase: GetReleaseUseCase,
    getUserRoleUseCase: GetUserRoleUseCase
):ViewModel() {

    private val _responsePremiere:MutableStateFlow<Premiere?> = MutableStateFlow(null)
    val responsePremiere = _responsePremiere.asStateFlow().filterNotNull()

    private val _responseShop:MutableStateFlow<Shop?> = MutableStateFlow(null)
    val responseShop = _responseShop.asStateFlow().filterNotNull()

    private val _responseCinema = MutableStateFlow(listOf<Cinema>())
    val responseCinema = _responseCinema.asStateFlow()

    val getUserRole = getUserRoleUseCase.invoke()

    private val _responsePlaylist = MutableStateFlow(listOf<Playlist>())
    val responsePlaylist = _responsePlaylist.asStateFlow()

    fun getPremiere(year:Int,month:String){
        viewModelScope.launch {
            val response = getPremiereUseCase.invoke(year, month)
            _responsePremiere.value = response
        }
    }

    fun getRelease(
        year: Int,
        month: String
    ): Flow<PagingData<ReleaseItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            ReleasePagingSource(
                year = year,
                mont = month,
                getReleaseUseCase = getReleaseUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getShop(){
        viewModelScope.launch {
            val response = getShopUseCase.invoke()
            _responseShop.value = response
        }
    }

    fun getCinema(
        search:String = "",
        has3D:Boolean? = null,
        has4D:Boolean? = null,
        hasImax:Boolean? = null
    ){
        viewModelScope.launch {
            val response = getCinemaUseCase.invoke(search, has3D, has4D, hasImax)
            _responseCinema.value = response
        }
    }

    fun getPlaylist(){
        viewModelScope.launch {
            val response = getPlaylistUseCase.invoke()
            _responsePlaylist.value = response
        }
    }
}