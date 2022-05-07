package com.example.feature_persons.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core_network_domain.model.person.PersonItem
import com.example.core_network_domain.useCase.person.GetSearchPersonUseCase
import com.example.feature_persons.source.PersonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonsViewModel @Inject constructor(
    private val getSearchPersonUseCase: GetSearchPersonUseCase
):ViewModel() {

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
}