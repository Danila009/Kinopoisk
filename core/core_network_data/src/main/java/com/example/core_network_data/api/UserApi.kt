package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.AUTHORIZATION_URL
import com.example.core_network_data.common.ConstantsUrl.REGISTRATION_URL
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.model.user.Registration
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST(AUTHORIZATION_URL)
    suspend fun authorization(
        @Body authorization: Authorization
    ): AuthorizationHeader

    @POST(REGISTRATION_URL)
    suspend fun registration(
        @Body registration: Registration
    ):Void
}