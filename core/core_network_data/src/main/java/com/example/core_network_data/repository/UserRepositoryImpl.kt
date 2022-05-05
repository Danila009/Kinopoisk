package com.example.core_network_data.repository

import com.example.core_network_data.api.UserApi
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override suspend fun authorization(authorization: Authorization): AuthorizationHeader {
        return userApi.authorization(authorization)
    }

    override suspend fun registration(registration: Registration):Void {
        return userApi.registration(registration)
    }
}