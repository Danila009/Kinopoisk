package com.example.feature_cinema_map.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.useCase.cinema.GetCinemaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CinemaMapViewModel @Inject constructor(
    private val getCinemaUseCase: GetCinemaUseCase
):ViewModel() {

    private val _responseCinema = MutableStateFlow(listOf<Cinema>())
    val responseCinema = _responseCinema.asStateFlow()

    fun getCinema(
        search:String = "",
        has3D:Boolean? = null,
        has4D:Boolean? = null,
        hasImax:Boolean? = null
    ) = viewModelScope.launch {
        val response = getCinemaUseCase.invoke(search, has3D, has4D, hasImax)
        _responseCinema.value = response
    }
}