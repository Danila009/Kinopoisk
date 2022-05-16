package com.example.feature_persons.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import com.example.core_utils.state.FilmSortingOrder
import com.example.core_utils.state.RiakAndMortyCharactersStatus
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

    private val _responseSearch:MutableState<String> = mutableStateOf("")
    val responseSearch:State<String> = _responseSearch

    private val _responseMovieTotal = MutableStateFlow(0)
    val responseMovieTotal = _responseMovieTotal.asStateFlow()

    private val _responsePersonTotal = MutableStateFlow(0)
    val responsePersonTotal = _responsePersonTotal.asStateFlow()

    private val _responseComicsTotal = MutableStateFlow(0)
    val responseComicsTotal = _responseComicsTotal.asStateFlow()

    private val _responseCharactersRiakAndMortyTotal = MutableStateFlow(0)
    val responseCharactersRiakAndMortyTotal = _responseCharactersRiakAndMortyTotal.asStateFlow()

    private val _responseOrderFilm:MutableState<FilmSortingOrder> =
        mutableStateOf(FilmSortingOrder.RATING)
    val responseOrderFilm:State<FilmSortingOrder> = _responseOrderFilm

    private val _responseRatingToFilm:MutableState<Int> = mutableStateOf(0)
    val responseRatingToFilm:State<Int> = _responseRatingToFilm

    private val _responseRatingFromFilm:MutableState<Int> = mutableStateOf(10)
    val responseRatingFromFilm:State<Int> = _responseRatingFromFilm

    private val _responseCinemaHas3D:MutableState<String> = mutableStateOf("--")
    val responseCinemaHas3D:State<String> = _responseCinemaHas3D

    private val _responseCinemaHas4D:MutableState<String> = mutableStateOf("--")
    val responseCinemaHas4D:State<String> = _responseCinemaHas4D

    private val _responseCinemaHasImax:MutableState<String> = mutableStateOf("--")
    val responseCinemaHasImax:State<String> = _responseCinemaHasImax

    private val _responseRiakAndMortyStatusCharacters:MutableState<RiakAndMortyCharactersStatus?> = mutableStateOf(null)
    val responseRiakAndMortyStatusCharacters:State<RiakAndMortyCharactersStatus?> =_responseRiakAndMortyStatusCharacters

    fun updateRiakAndMortyCharactersStatus(riakAndMortyCharactersStatus:RiakAndMortyCharactersStatus){
        _responseRiakAndMortyStatusCharacters.value = riakAndMortyCharactersStatus
    }

    fun updateCinemaHas3D(has3D: String){
        _responseCinemaHas3D.value = has3D
    }

    fun updateCinemaHas4D(has4D: String){
        _responseCinemaHas4D.value = has4D
    }

    fun updateCinemaHasImax(imax: String){
        _responseCinemaHasImax.value = imax
    }

    fun updateSearch(search: String){
        _responseSearch.value = search
    }

    fun updateRatingToFilm(ratingTo: Int){
        _responseRatingToFilm.value = ratingTo
    }

    fun updateRatingFromFilm(ratingFrom: Int){
        _responseRatingFromFilm.value = ratingFrom
    }

    fun updateOrderFilm(filmSortingOrder: FilmSortingOrder){
        _responseOrderFilm.value = filmSortingOrder
    }

    fun getSearchPerson(
        name:String
    ): Flow<PagingData<PersonItem>> {

        viewModelScope.launch {
            try {
                val response = getSearchPersonUseCase.invoke(
                    name, page = 1
                )
                _responsePersonTotal.value = response.total
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
        }

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
            try {
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
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
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

        viewModelScope.launch {
            try {
                viewModelScope.launch {
                    val response = getComicsMarvelUseCase.invoke(
                        search = search
                    )?.data?.total

                    _responseComicsTotal.value = response ?: 0
                }
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
        }

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

        viewModelScope.launch {
            try {
                val response = getCharactersUseCase.invoke(
                    search, if (status == "--") "" else status, species, type, gender, 1
                ).info?.count ?: 0

                _responseCharactersRiakAndMortyTotal.value = response
            }catch (e:Exception){
                Log.e(RETROFIT_TAG, e.message.toString())
            }
        }

        return Pager(PagingConfig(pageSize = 1)){
            CharactersRickAndMortySource(
                search, if (status == "--") "" else status, species, type, gender, getCharactersUseCase
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