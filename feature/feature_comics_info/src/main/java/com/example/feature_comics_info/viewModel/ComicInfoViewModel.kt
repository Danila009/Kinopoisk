package com.example.feature_comics_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.character.Result
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.source.CharacterMarvelComicSource
import com.example.core_network_domain.useCase.marvel.GetComicByIdUseCase
import com.example.core_network_domain.useCase.marvel.GetComicCharactersUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ComicInfoViewModel @Inject constructor(
    private val getComicByIdUseCase: GetComicByIdUseCase,
    private val getComicCharactersUseCase: GetComicCharactersUseCase
): ViewModel() {

    private val _responseComic:MutableStateFlow<Response<ComicsMarvel>> =
        MutableStateFlow(Response.Loading())
    val responseComic = _responseComic.asStateFlow()

    fun getComicById(comicId:Int){
        getComicByIdUseCase.invoke(comicId).onEach {
            _responseComic.value = it
        }.launchIn(viewModelScope)
    }

    fun getComicCharacters(comicId: Int): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = 1)){
            CharacterMarvelComicSource(
                id = comicId,
                getComicCharactersUseCase = getComicCharactersUseCase
            )
        }.flow.cachedIn(viewModelScope)
    }
}