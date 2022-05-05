package com.example.core_database_domain.useCase.user

import com.example.core_database_domain.repository.UserDatabaseRepository
import javax.inject.Inject

class SavaUserRoleUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    suspend operator fun invoke(userRole: String){
        userDatabaseRepository.savaUserRole(userRole = enumValueOf(userRole))
    }
}