package com.example.core_network_domain.useCase.user

import com.example.core_network_domain.repository.UserRepository
import javax.inject.Inject

class PutUpdatePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email:String, password:String):String {
        return userRepository.putUpdatePassword(email, password)
    }
}