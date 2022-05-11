package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.model.cinema.Schedule
import com.example.core_network_domain.repository.CinemaRepository
import javax.inject.Inject

class GetCinemaScheduleUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    suspend operator fun invoke(id:Int):List<Schedule>{
        return cinemaRepository.getCinemaSchedule(id)
    }
}