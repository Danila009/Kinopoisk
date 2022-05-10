package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.ImageMovie
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int,type:String,page:Int):ImageMovie{
        return movieRepository.getImage(id, type, page)
    }
}