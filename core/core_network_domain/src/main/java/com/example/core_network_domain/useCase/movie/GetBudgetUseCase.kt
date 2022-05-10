package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetBudgetUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int):Budget{
        return movieRepository.getBudget(id)
    }
}