package com.example.core_database_domain.useCase.user

import com.example.core_database_domain.repository.UserDatabaseRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    operator fun invoke(token:String){
        userDatabaseRepository.saveToken(token)
    }
}