package com.example.core_network_domain.useCase.user

import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke():User = userRepository.getUserInfo()
}