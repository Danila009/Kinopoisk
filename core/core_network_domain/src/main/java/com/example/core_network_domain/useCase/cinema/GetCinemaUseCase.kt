package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.repository.CinemaRepository
import javax.inject.Inject

class GetCinemaUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    suspend operator fun invoke(
        search:String,
        has3D:Boolean?,
        has4D:Boolean?,
        hasImax:Boolean?
    ):List<Cinema> = cinemaRepository.getCinema(search, has3D, has4D, hasImax)
}