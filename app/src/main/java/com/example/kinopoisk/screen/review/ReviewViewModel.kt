package com.example.kinopoisk.screen.review

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.core_network_domain.model.movie.review.ReviewDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {

    private val _responseReviewDetail:MutableStateFlow<ReviewDetail> = MutableStateFlow(ReviewDetail())
    val responseReviewDetail:StateFlow<ReviewDetail> = _responseReviewDetail

    fun getReviewDetail(id:Int){
        viewModelScope.launch {
            try {
                _responseReviewDetail.value =  apiRepository.getReviewDetail(id = id).body()!!
            }catch (e:Exception){
                Log.d("Retrofit", e.message.toString())
            }
        }
    }
}