package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.AUTHORIZATION_URL
import com.example.core_network_data.common.ConstantsUrl.REGISTRATION_URL
import com.example.core_network_data.common.ConstantsUrl.UPDATE_PASSWORD_URL
import com.example.core_network_data.common.ConstantsUrl.UPDATE_PHOTO_URL
import com.example.core_network_data.common.ConstantsUrl.USER_INFO_URL
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.model.user.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST(AUTHORIZATION_URL)
    suspend fun authorization(
        @Body authorization: Authorization
    ): Response<AuthorizationHeader>

    @POST(REGISTRATION_URL)
    suspend fun registration(
        @Body registration: Registration
    ):Response<Void?>

    @GET(USER_INFO_URL)
    suspend fun getUserInfo():Response<User>

    @PUT(UPDATE_PASSWORD_URL)
    suspend fun patchUpdatePassword(
        @Query("email") email:String,
        @Query("password") password:String
    ):Response<Void?>

    @Multipart
    @PATCH(UPDATE_PHOTO_URL)
    suspend fun patchUpdatePhotoUser(
        @Part file: MultipartBody.Part
    ):Response<Void?>
}