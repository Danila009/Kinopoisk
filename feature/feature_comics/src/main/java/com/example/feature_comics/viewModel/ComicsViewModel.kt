package com.example.feature_comics.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.useCase.marvel.GetComicsMarvelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ComicsViewModel @Inject constructor(
    private val getComicsMarvelUseCase: GetComicsMarvelUseCase
):ViewModel() {

    private val _responseComicsMarvel:MutableStateFlow<Response<ComicsMarvel>> =
        MutableStateFlow(Response.Loading())
    val responseComicsMarvel = _responseComicsMarvel.asStateFlow()

    fun getComicsMarvel(search:String) =
        getComicsMarvelUseCase.invoke(search).onEach {
            _responseComicsMarvel.value = it
    }.launchIn(viewModelScope)
}