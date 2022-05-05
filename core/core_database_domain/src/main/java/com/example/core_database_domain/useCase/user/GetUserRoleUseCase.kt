package com.example.core_database_domain.useCase.user

import com.example.core_database_domain.common.UserRole
import com.example.core_database_domain.repository.UserDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserRoleUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    operator fun invoke():Flow<UserRole>{
        return userDatabaseRepository.getUserRole()
    }
}