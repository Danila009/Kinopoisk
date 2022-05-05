package com.example.core_network_domain.useCase.user

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(authorization:Authorization):Flow<Response<AuthorizationHeader>> = flow {
        try {
            val response = userRepository.authorization(authorization)
            emit(Response.Success(response))
        }catch (e:Exception){
            emit(Response.Error<AuthorizationHeader>(e.message.toString()))
        }
    }
}