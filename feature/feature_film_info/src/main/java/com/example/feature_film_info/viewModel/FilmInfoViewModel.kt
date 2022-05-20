package com.example.feature_film_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_database_domain.useCase.user.GetStatusRegistrationUseCase
import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.ImageMovieItem
import com.example.core_network_domain.model.movie.SequelAndPrequel
import com.example.core_network_domain.model.movie.Similar
import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_network_domain.model.movie.history.HistoryMovieItem
import com.example.core_network_domain.model.movie.review.ReviewItem
import com.example.core_network_domain.model.movie.staff.Staff
import com.example.core_network_domain.model.movie.video.Video
import com.example.core_network_domain.model.serial.Season
import com.example.core_network_domain.source.ImageMoviePagingSource
import com.example.core_network_domain.source.ReviewMoviePagingSource
import com.example.core_network_domain.useCase.IMDb.GetFilmAwardUseCase
import com.example.core_network_domain.useCase.IMDb.GetFilmFAQUseCase
import com.example.core_network_domain.useCase.IMDb.GetFilmWikipediaInfoUseCase
import com.example.core_network_domain.useCase.history.PostHistoryMovieUseCase
import com.example.core_network_domain.useCase.movie.*
import com.example.core_network_domain.useCase.movieVideo.GetMovieVideoUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class FilmInfoViewModel @Inject constructor(
    private val getFilmInfoUseCase: GetFilmInfoUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val getFactUseCase: GetFactUseCase,
    private val getStaffUseCase: GetStaffUseCase,
    private val getSimilarUseCase: GetSimilarUseCase,
    private val getSequelAndPrequelUseCase:GetSequelAndPrequelUseCase,
    private val getSeasonUseCase: GetSeasonUseCase,
    private val getDistributionUseCase: GetDistributionUseCase,
    private val getImageMovieUseCase: GetImageUseCase,
    private val getReviewMovieUseCase: GetReviewMovieUseCase,
    private val postHistoryMovieUseCase: PostHistoryMovieUseCase,
    private val getFilmAwardUseCase: GetFilmAwardUseCase,
    private val getFilmFAQUseCase: GetFilmFAQUseCase,
    private val getFilmWikipediaInfoUseCase: GetFilmWikipediaInfoUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    getStatusRegistrationUseCase: GetStatusRegistrationUseCase
):ViewModel() {

    private val _responseFilmInfo: MutableStateFlow<FilmInfo> = MutableStateFlow(FilmInfo())
    val responseFilmInfo: StateFlow<FilmInfo> = _responseFilmInfo.asStateFlow()

    private val _responseBudget:MutableStateFlow<Budget> = MutableStateFlow(Budget())
    val responseBudget:StateFlow<Budget> = _responseBudget.asStateFlow()

    private val _responseFact:MutableStateFlow<Fact> = MutableStateFlow(Fact())
    val responseFact:StateFlow<Fact> = _responseFact.asStateFlow()

    private val _responseStaff:MutableStateFlow<List<Staff>> = MutableStateFlow(listOf())
    val responseStaff:StateFlow<List<Staff>> = _responseStaff.asStateFlow()

    private val _responseSimilar:MutableStateFlow<Similar> = MutableStateFlow(Similar())
    val responseSimilar:StateFlow<Similar> = _responseSimilar.asStateFlow()

    private val _responseSequelAndPrequel:MutableStateFlow<List<SequelAndPrequel>> = MutableStateFlow(listOf())
    val responseSequelAndPrequel:StateFlow<List<SequelAndPrequel>> = _responseSequelAndPrequel.asStateFlow()

    private val _responseSeason:MutableStateFlow<Season> = MutableStateFlow(Season())
    val responseSeason:StateFlow<Season> = _responseSeason.asStateFlow()

    private val _responseDistribution:MutableStateFlow<Distribution> = MutableStateFlow(Distribution())
    val responseDistribution:StateFlow<Distribution> = _responseDistribution.asStateFlow()

    private val _responseFilmAward:MutableStateFlow<Award?> = MutableStateFlow(null)
    val responseFilmAward = _responseFilmAward.asStateFlow().filterNotNull()

    private val _responseFilmFAQ:MutableStateFlow<FAQ?> = MutableStateFlow(null)
    val responseFilmFAQ = _responseFilmFAQ.asStateFlow().filterNotNull()

    private val _responseFilmWikipedia:MutableStateFlow<Wikipedia?> = MutableStateFlow(null)
    val responseFilmWikipedia = _responseFilmWikipedia.asStateFlow().filterNotNull()

    private val _responseMovieVideo:MutableStateFlow<Video?> = MutableStateFlow(null)
    val responseMovieVideo = _responseMovieVideo.asStateFlow().filterNotNull()

    val responseStatusRegistration = getStatusRegistrationUseCase.invoke()

    fun getFilmInfo(id:Int){
        getFilmInfoUseCase.invoke(id).onEach {
            it.data?.let { filmInfo ->
                _responseFilmInfo.value = filmInfo
            }
        }.launchIn(viewModelScope)
    }

    fun getBudget(id: Int) = viewModelScope.launch {
        val response = getBudgetUseCase.invoke(id)
        _responseBudget.value = response
    }

    fun getFact(id: Int) = viewModelScope.launch {
        val response = getFactUseCase.invoke(id)
        _responseFact.value = response
    }

    fun getStaff(id: Int) = viewModelScope.launch {
        val response = getStaffUseCase.invoke(id)
        _responseStaff.value = response
    }

    fun getSimilar(id: Int) = viewModelScope.launch {
        val response = getSimilarUseCase.invoke(id)
        _responseSimilar.value = response
    }

    fun getSequelAndPrequel(id: Int) = viewModelScope.launch {
        val response = getSequelAndPrequelUseCase.invoke(id)
        _responseSequelAndPrequel.value = response
    }

    fun getSeason(id: Int) = viewModelScope.launch {
        val response = getSeasonUseCase.invoke(id)
        _responseSeason.value = response
    }

    fun getDistribution(id: Int) = viewModelScope.launch {
        val response = getDistributionUseCase.invoke(id)
        _responseDistribution.value = response
    }

    fun getImage(
        id: Int,
        type:String
    ): Flow<PagingData<ImageMovieItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            ImageMoviePagingSource(
                getImageMovieUseCase = getImageMovieUseCase,
                id = id,
                type = type
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getReview(
        id: Int
    ): Flow<PagingData<ReviewItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            ReviewMoviePagingSource(
                getReviewMovieUseCase = getReviewMovieUseCase,
                id = id
            )
        }.flow.cachedIn(viewModelScope)
    }

    @ExperimentalSerializationApi
    fun postHistoryMovie(
        historyMovieItem: HistoryMovieItem
    ) = viewModelScope.launch {
        postHistoryMovieUseCase.invoke(historyMovieItem)
    }

    fun getFilmAward(id:String){
        getFilmAwardUseCase.invoke(id).onEach {
            it.data?.let { award ->
                _responseFilmAward.value = award
            }
        }.launchIn(viewModelScope)
    }

    fun getFilmFAQ(id: String){
        getFilmFAQUseCase.invoke(id).onEach {
            it.data?.let { FAQ ->
                _responseFilmFAQ.value = FAQ
            }
        }.launchIn(viewModelScope)
    }

    fun getFilmWikipedia(id: String){
        getFilmWikipediaInfoUseCase.invoke(id).onEach {
            it.data?.let { wikipedia ->
                _responseFilmWikipedia.value = wikipedia
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieVideo(id: Int) = viewModelScope.launch {
        val response = getMovieVideoUseCase.invoke(id)
        _responseMovieVideo.value = response
    }
}