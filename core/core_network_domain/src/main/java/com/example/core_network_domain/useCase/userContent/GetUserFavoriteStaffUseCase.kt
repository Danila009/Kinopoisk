package com.example.core_network_domain.useCase.userContent

import com.example.core_network_domain.model.staff.Staff
import com.example.core_network_domain.repository.UserContentRepository
import javax.inject.Inject

class GetUserFavoriteStaffUseCase @Inject constructor(
    private val userContentRepository: UserContentRepository
) {
    suspend operator fun invoke():Staff{
        return userContentRepository.getUserFavoriteStaff()
    }
}