package com.example.core_database_domain.repository

import com.example.core_database_domain.common.UserRole
import kotlinx.coroutines.flow.Flow

interface UserDatabaseRepository {

    fun saveToken(token:String)

    suspend fun saveStatusRegistration(userRegistration:Boolean)

    suspend fun savaUserRole(userRole: UserRole)

    fun getUserRole():Flow<UserRole>
}