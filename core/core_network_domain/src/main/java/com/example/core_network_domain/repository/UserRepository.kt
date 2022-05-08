package com.example.core_network_domain.repository

import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.model.user.User

interface UserRepository {

    suspend fun authorization(
        authorization: Authorization
    ): AuthorizationHeader

    suspend fun registration(
        registration: Registration
    ):Void

    suspend fun getUserInfo():User

    suspend fun putUpdatePassword(
        email:String,
        password:String
    ):String
}