package com.example.core_network_domain.useCase.userContent

import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.repository.UserContentRepository
import javax.inject.Inject

class GetUserWatchLaterUseCase @Inject constructor(
    private val userContentRepository: UserContentRepository
) {
    suspend operator fun invoke():Movie{
        return userContentRepository.getUserWatchLater()
    }
}