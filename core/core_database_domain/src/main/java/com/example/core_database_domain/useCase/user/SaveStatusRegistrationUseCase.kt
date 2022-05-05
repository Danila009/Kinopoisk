package com.example.core_database_domain.useCase.user

import com.example.core_database_domain.repository.UserDatabaseRepository
import javax.inject.Inject

class SaveStatusRegistrationUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    suspend operator fun invoke(userRegistration:Boolean){
        userDatabaseRepository.saveStatusRegistration(userRegistration = userRegistration)
    }
}