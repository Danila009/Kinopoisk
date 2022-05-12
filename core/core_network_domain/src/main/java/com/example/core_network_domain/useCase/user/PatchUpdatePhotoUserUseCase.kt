package com.example.core_network_domain.useCase.user

import com.example.core_network_domain.repository.UserRepository
import javax.inject.Inject

class PatchUpdatePhotoUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(byteArray: ByteArray){
        userRepository.patchUpdatePhotoUser(byteArray)
    }
}