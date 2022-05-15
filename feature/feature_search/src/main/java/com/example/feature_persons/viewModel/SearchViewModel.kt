package com.example.feature_persons.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.marvel.comics.Result
import com.example.core_network_domain.model.movie.FilmItem
import com.example.core_network_domain.model.movie.history.HistorySearchItem
import com.example.core_network_domain.model.person.PersonItem
import com.example.core_network_domain.model.rickAndMorty.CharacterItem
import com.example.core_network_domain.model.rickAndMorty.EpisodeItem
import com.example.core_network_domain.model.rickAndMorty.LocationItem
import com.example.core_network_domain.source.*
import com.example.core_network_domain.useCase.cinema.GetCinemaUseCase
import com.example.core_network_domain.useCase.history.GetHistoryMovieUseCase
import com.example.core_network_domain.useCase.history.GetHistorySearchUseCase
import com.example.core_network_domain.useCase.history.PostHistorySearchUseCase
import com.example.core_network_domain.useCase.marvel.GetComicsMarvelUseCase
import com.example.core_network_domain.useCase.movie.GetFilmUseCase
import com.example.core_network_domain.useCase.person.GetSearchPersonUseCase
import com.example.core_network_domain.useCase.rickAndMorty.GetCharactersUseCase
import com.example.core_network_domain.useCase.rickAndMorty.GetEpisodesUseCase
import com.example.core_network_domain.useCase.rickAndMorty.GetLocationsUseCase
import com.example.core_utils.common.Tag.RETROFIT_TAG
import com.example.feature_persons.source.PersonPagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class SearchViewModel @Inject constructor(
    private val getSearchPersonUseCase: GetSearchPersonUseCase,
    private val getCinemaUseCase: GetCinemaUseCase,
    private val getFilmUseCase: GetFilmUseCase,
    private val getComicsMarvelUseCase: GetComicsMarvelUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val postHistorySearchUseCase: PostHistorySearchUseCase,
    getHistorySearchUseCase: GetHistorySearchUseCase,
    getHistoryMovieUseCase: GetHistoryMovieUseCase
):ViewModel() {

    private val _responseCinema:MutableStateFlow<Response<List<Cinema>>> =
        MutableStateFlow(Response.Loading())
    val responseCinema = _responseCinema.asStateFlow()

    val responseHistorySearch = getHistorySearchUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Eagerly, Response.Loading()
    )

    val responseHistoryMovie = getHistoryMovieUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Eagerly, Response.Loading()
    )

    private val _responseMovieTotal = MutableStateFlow(0)
    val responseMovieTotal = _responseMovieTotal.asStateFlow()

    fun getSearchPerson(
        name:String
    ): Flow<PagingData<PersonItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            PersonPagingSource(
                getSearchPersonUseCase = getSearchPersonUseCase,
                name = name
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getCinema(
        search:String = "",
        has3D:Boolean? = null,
        has4D:Boolean? = null,
        hasImax:Boolean? = null
    ){
        getCinemaUseCase.invoke(
            search, has3D, has4D, hasImax
        ).onEach {
            _responseCinema.value = it
        }.launchIn(viewModelScope)
    }

    fun getFilm(
        genres:List<Int> = listOf(),
        countries:List<Int> = listOf(),
        order:String = "RATING",
        type:String = "ALL",
        ratingFrom:Int = 0,
        ratingTo:Int = 10,
        yearFrom:Int = 1000,
        yearTo:Int = 3000,
        keyword:String = ""
    ): Flow<PagingData<FilmItem>> {

        viewModelScope.launch {
            val total = getFilmUseCase.invoke(
                genres = genres,
                countries = countries,
                order = order,
                type = type,
                ratingFrom = ratingFrom,
                ratingTo =ratingTo,
                yearFrom = yearFrom,
                yearTo = yearTo,
                keyword = keyword,
                page = 1
            ).total
            _responseMovieTotal.value = total
        }

        return  Pager(PagingConfig(pageSize = 1)){
            FilmPagingSource(
                genres = genres,
                countries = countries,
                order = order,
                type = type,
                ratingFrom = ratingFrom,
                ratingTo = ratingTo,
                yearFrom = yearFrom,
                yearTo = yearTo,
                keyword = keyword,
                getFilmUseCase = getFilmUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getComicsMarvel(
        search:String = ""
    ): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = 1)){
            ComicsMarvelSource(
                search = search,
                getComicsMarvelUseCase = getComicsMarvelUseCase
            )
        }.flow.cachedIn(viewModelScope)
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
        search:String = "",
        dimension:String = "",
    ):Flow<PagingData<LocationItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            LocationsRickAndMortySource(
                search, dimension, getLocationsUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getEpisodeRickAndMorty(
        search: String = "",
        episode:Int? = null
    ):Flow<PagingData<EpisodeItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            EpisodesRickAndMortySource(
                getEpisodesUseCase = getEpisodesUseCase,
                search = search,
                episode = episode
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun postHistorySearch(historySearchItem: HistorySearchItem) = viewModelScope.launch {
        try {
            postHistorySearchUseCase.invoke(
                historySearchItem = historySearchItem
            )
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
        }
    }
}