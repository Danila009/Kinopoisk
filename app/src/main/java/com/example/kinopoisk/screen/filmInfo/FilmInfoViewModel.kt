package com.example.kinopoisk.screen.filmInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.filmInfo.*
import com.example.kinopoisk.api.model.filmInfo.distribution.Distribution
import com.example.kinopoisk.api.model.review.ReviewItem
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.series.Serial
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.api.model.user.Purchase
import com.example.kinopoisk.api.model.user.history.History
import com.example.kinopoisk.screen.filmInfo.source.ImagePagingSource
import com.example.kinopoisk.screen.filmInfo.source.ReviewPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FilmInfoViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val apiUserRepository: ApiUserRepository
):ViewModel() {
    private val _responseFilmInfo:MutableStateFlow<FilmInfo> = MutableStateFlow(FilmInfo())
    val responseFilmInfo:StateFlow<FilmInfo> = _responseFilmInfo.asStateFlow()
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
    private val _responseUserFavoriteCheck:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val responseUserFavoriteCheck:StateFlow<Boolean> = _responseUserFavoriteCheck.asStateFlow()
    private val _responseShopCheck:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val responseShopCheck:StateFlow<Boolean> = _responseShopCheck.asStateFlow()
    private val _responseShopId:MutableStateFlow<Shop> = MutableStateFlow(Shop())
    val responseShopId:StateFlow<Shop> = _responseShopId.asStateFlow()
    private val _responsePurchaseCheck:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val responsePurchaseCheck:StateFlow<Boolean> = _responsePurchaseCheck.asStateFlow()
    private val _responseSeriesCheck:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val responseSeriesCheck:StateFlow<Boolean> = _responseSeriesCheck.asStateFlow()

    fun getFilmInfo(id:Int){
        viewModelScope.launch {
            try {
                _responseFilmInfo.value = apiRepository.getFilmInfo(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getBudget(id: Int){
        viewModelScope.launch {
            try {
                _responseBudget.value = apiRepository.getBudget(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getFact(id: Int){
        viewModelScope.launch {
            try {
                _responseFact.value = apiRepository.getFact(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getStaff(id: Int){
        viewModelScope.launch {
            try {
                _responseStaff.value = apiRepository.getStaff(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSimilar(id: Int){
        viewModelScope.launch {
            try {
                _responseSimilar.value = apiRepository.getSimilar(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSequelAndPrequel(id: Int){
        viewModelScope.launch {
            try {
                _responseSequelAndPrequel.value = apiRepository.getSequelAndPrequel(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSeason(id: Int){
        viewModelScope.launch {
            try {
                _responseSeason.value = apiRepository.getSeason(id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getImage(
        id: Int,
        type:String
    ): Flow<PagingData<ImageItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            ImagePagingSource(
                apiRepository = apiRepository,
                id = id,
                type = type
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun getReview(
        id: Int
    ):Flow<PagingData<ReviewItem>>{
        return Pager(PagingConfig(pageSize = 1)){
            ReviewPagingSource(
                apiRepository = apiRepository,
                id = id
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun postFavoriteFilm(
        filmItem: FilmItem
    ){
        viewModelScope.launch {
            try {
                apiUserRepository.postFavoriteFilm(
                    filmItem = filmItem
                )
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getDistribution(id: Int){
        viewModelScope.launch {
            try {
                _responseDistribution.value = apiRepository.getDistribution(id = id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getUserFavoriteCheck(kinopoiskId:Int){
        viewModelScope.launch {
            try {
                _responseUserFavoriteCheck.value = apiUserRepository.getUserFavoriteCheck(kinopoiskId = kinopoiskId).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun deleteFavoriteFilm(kinopoiskId: Int){
        viewModelScope.launch {
            try {
                apiUserRepository.deleteFavoriteFilm(kinopoiskId = kinopoiskId)
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun postHistory(history: History){
        viewModelScope.launch {
            try {
                apiUserRepository.postHistory(
                    history = history
                )
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getShopCheck(idKinopoisk:Int){
        viewModelScope.launch {
            try {
                _responseShopCheck.value = apiUserRepository.getShopCheck(idKinopoisk = idKinopoisk).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getShopId(idKinopoisk: Int){
        viewModelScope.launch {
            try {
                _responseShopId.value = apiUserRepository.getShopId(idKinopoisk = idKinopoisk).body()!!
            }catch (e:Exception) {
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun postPurchase(purchase: Purchase){
        viewModelScope.launch {
            try {
                apiUserRepository.postPurchase(purchase)
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getPurchase(idKinopoisk: Int){
        viewModelScope.launch {
            try {
                _responsePurchaseCheck.value = apiUserRepository.getPurchase(idKinopoisk = idKinopoisk).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun getSeriesCheck(
        kinopoiskId:Int,
        season:Int,
        series:Int
    ){
        viewModelScope.launch {
            try {
                _responseSeriesCheck.value = apiUserRepository.getSeriesCheck(
                    kinopoiskId = kinopoiskId,
                    season = season,
                    series = series
                ).body()!!
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }

    fun postSeries(serial: Serial){
        viewModelScope.launch {
            try {
                apiUserRepository.postSeries(
                    serial = serial
                )
            }catch (e:Exception){
                Log.d("Retrofit:",e.message.toString())
            }
        }
    }
}