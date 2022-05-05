package com.example.core_network_domain.useCase.user

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(registration: Registration):Flow<Response<Void>> = flow {
        try {
            val response = userRepository.registration(registration)
            emit(Response.Success(data = response))
        }catch (e:Exception){
            emit(Response.Error<Void>(message = e.message.toString()))
        }
    }
}