package com.example.feature_cinema_map.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.route.Route
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.useCase.cinema.GetCinemaUseCase
import com.example.core_network_domain.useCase.route.GetRouteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CinemaMapViewModel @Inject constructor(
    private val getCinemaUseCase: GetCinemaUseCase,
    private val getRouteUseCase: GetRouteUseCase
):ViewModel() {

    private val _responseCinema:MutableStateFlow<Response<List<Cinema>>> = MutableStateFlow(Response.Loading())
    val responseCinema = _responseCinema.asStateFlow()

    private val _responseRoute:MutableStateFlow<Response<Route>?> = MutableStateFlow(null)
    val responseRoute = _responseRoute.asStateFlow()

    fun getRoute(
        start:String,
        end:String
    ){
        getRouteUseCase.invoke(start, end).onEach {
            _responseRoute.value = it
        }.launchIn(viewModelScope)
    }

    fun getCinema(
        search:String = "",
        has3D:Boolean? = null,
        has4D:Boolean? = null,
        hasImax:Boolean? = null
    ) {
        getCinemaUseCase.invoke(
            search, has3D, has4D, hasImax
        ).onEach { response ->
            _responseCinema.value = response
        }.launchIn(viewModelScope)
    }
}