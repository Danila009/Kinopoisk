package com.example.feature_serial_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.rickAndMorty.CharacterItem
import com.example.core_network_domain.model.rickAndMorty.LocationItem
import com.example.core_network_domain.model.serial.Season
import com.example.core_network_domain.useCase.movie.GetFilmInfoUseCase
import com.example.core_network_domain.useCase.movie.GetSeasonUseCase
import com.example.core_network_domain.useCase.rickAndMorty.GetCharactersUseCase
import com.example.core_network_domain.useCase.rickAndMorty.GetLocationsUseCase
import com.example.core_network_domain.source.CharactersRickAndMortySource
import com.example.core_network_domain.source.LocationsRickAndMortySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SerialInfoViewModel @Inject constructor(
    private val getFilmInfoUseCase: GetFilmInfoUseCase,
    private val getSeasonUseCase: GetSeasonUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getLocationsUseCase: GetLocationsUseCase
):ViewModel() {

    private val _responseFilmInfo: MutableStateFlow<FilmInfo> = MutableStateFlow(FilmInfo())
    val responseFilmInfo: StateFlow<FilmInfo> = _responseFilmInfo.asStateFlow()

    private val _responseSeason:MutableStateFlow<Season> = MutableStateFlow(Season())
    val responseSeason:StateFlow<Season> = _responseSeason.asStateFlow()

    fun getFilmInfo(id:Int) = viewModelScope.launch {
        val response = getFilmInfoUseCase.invoke(id)
        _responseFilmInfo.value = response
    }

    fun getSeason(id: Int) = viewModelScope.launch {
        val response = getSeasonUseCase.invoke(id)
        _responseSeason.value = response
    }

    fun getCharactersRickAndMorty(
        search: String = "",
        status: String = "",
        species: String = "",
        type: String = "",
        gender: String = "",
    ):Flow<PagingData<CharacterItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            CharactersRickAndMortySource(
                search, status, species, type, gender, getCharactersUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getLocationsRickAndMorty(
        name:String = "",
        dimension:String = "",
    ):Flow<PagingData<LocationItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            LocationsRickAndMortySource(
                name, dimension, getLocationsUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }
}