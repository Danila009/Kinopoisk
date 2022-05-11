package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.model.cinema.Photo
import com.example.core_network_domain.repository.CinemaRepository
import javax.inject.Inject

class GetCinemaPhotosUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    suspend operator fun invoke(id:Int):List<Photo>{
        return cinemaRepository.getCinemaPhotos(id)
    }
}