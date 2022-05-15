package com.example.feature_film_images.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.ImageMovieItem
import com.example.core_network_domain.source.ImageMoviePagingSource
import com.example.core_network_domain.useCase.movie.GetFilmInfoUseCase
import com.example.core_network_domain.useCase.movie.GetImageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmImagesViewModel @Inject constructor(
    private val getFilmInfoUseCase: GetFilmInfoUseCase,
    private val getFilmImageUseCase: GetImageUseCase
):ViewModel() {

    private val _responseFilmInfo:MutableStateFlow<FilmInfo?> = MutableStateFlow(null)
    val responseFilmInfo = _responseFilmInfo.asStateFlow().filterNotNull()

    fun getFilmInfo(id:Int) = viewModelScope.launch {
        val response = getFilmInfoUseCase.invoke(id)
        _responseFilmInfo.value = response
    }

    fun getFilmImages(
        id: Int,
        type:String
    ): Flow<PagingData<ImageMovieItem>> {
        return Pager(PagingConfig(pageSize = 1)){
            ImageMoviePagingSource(
                getImageMovieUseCase = getFilmImageUseCase,
                id = id,
                type = type
            )
        }.flow.cachedIn(viewModelScope)
    }
}