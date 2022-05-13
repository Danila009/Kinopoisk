package com.example.feature_cinema_add_review.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.model.cinema.CinemaAddReview
import com.example.core_network_domain.useCase.cinema.GetCinemaByIdUseCase
import com.example.core_network_domain.useCase.cinema.PostCinemaReviewUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CinemaAddReviewViewModel @Inject constructor(
    private val postCinemaReviewUseCase: PostCinemaReviewUseCase,
    private val getCinemaByIdUseCase: GetCinemaByIdUseCase
):ViewModel() {

    private val _responseCinema:MutableStateFlow<Cinema?> = MutableStateFlow(null)
    val responseCinema = _responseCinema.asStateFlow().filterNotNull()

    fun getCinemaById(id: Int){
        getCinemaByIdUseCase.invoke(id).onEach {
            it.data?.let { cinema ->
                _responseCinema.value = cinema
            }
        }.launchIn(viewModelScope)
    }

    fun postReview(id:Int,review:CinemaAddReview) = viewModelScope.launch {
        postCinemaReviewUseCase.invoke(
            cinemaId = id,
            review = review
        )
    }
}