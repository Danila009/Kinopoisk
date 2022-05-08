package com.example.feature_sorting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.model.movie.Filter
import com.example.core_network_domain.useCase.movie.GetFilterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortingViewModel @Inject constructor(
    private val getFilterUseCase: GetFilterUseCase
):ViewModel() {

    private val _responseFilter: MutableStateFlow<Filter> = MutableStateFlow(Filter())
    val responseFilter: StateFlow<Filter> = _responseFilter.asStateFlow()

    fun getFilter() = viewModelScope.launch {
        val response = getFilterUseCase.invoke()
        _responseFilter.value = response
    }
}