package com.example.feature_comics.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.model.marvel.comics.Result
import com.example.core_network_domain.source.ComicsMarvelSource
import com.example.core_network_domain.useCase.marvel.GetComicsMarvelUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ComicsViewModel @Inject constructor(
    private val getComicsMarvelUseCase: GetComicsMarvelUseCase,
):ViewModel() {

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
}