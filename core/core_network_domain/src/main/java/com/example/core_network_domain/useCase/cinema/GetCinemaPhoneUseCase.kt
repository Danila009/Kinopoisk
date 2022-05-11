package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.model.cinema.Phone
import com.example.core_network_domain.repository.CinemaRepository
import javax.inject.Inject

class GetCinemaPhoneUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    suspend operator fun invoke(id:Int):List<Phone>{
        return cinemaRepository.getCinemaPhone(id)
    }
}