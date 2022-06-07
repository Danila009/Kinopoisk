package com.example.feature_comics_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.useCase.marvel.GetComicByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ComicInfoViewModel @Inject constructor(
    private val getComicByIdUseCase: GetComicByIdUseCase
): ViewModel() {

    private val _responseComic:MutableStateFlow<Response<ComicsMarvel>> =
        MutableStateFlow(Response.Loading())
    val responseComic = _responseComic.asStateFlow()

    fun getComicById(comicId:Int){
        getComicByIdUseCase.invoke(comicId).onEach {
            _responseComic.value = it
        }.launchIn(viewModelScope)
    }
}