package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.staff.Staff
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetStaffUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int):List<Staff>{
        return movieRepository.getStaff(id)
    }
}