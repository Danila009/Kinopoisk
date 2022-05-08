package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.AUTHORIZATION_URL
import com.example.core_network_data.common.ConstantsUrl.REGISTRATION_URL
import com.example.core_network_data.common.ConstantsUrl.UPDATE_PASSWORD_URL
import com.example.core_network_data.common.ConstantsUrl.USER_INFO_URL
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.model.user.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST(AUTHORIZATION_URL)
    suspend fun authorization(
        @Body authorization: Authorization
    ): AuthorizationHeader

    @POST(REGISTRATION_URL)
    suspend fun registration(
        @Body registration: Registration
    ):Void

    @GET(USER_INFO_URL)
    suspend fun getUserInfo():Response<User>

    @PUT(UPDATE_PASSWORD_URL)
    suspend fun putUpdatePassword(
        @Query("email") email:String,
        @Query("password") password:String
    ):Response<Void?>
}