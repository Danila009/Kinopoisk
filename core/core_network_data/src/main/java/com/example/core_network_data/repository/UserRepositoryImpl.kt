package com.example.core_network_data.repository

import com.example.core_network_data.api.UserApi
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.AuthorizationHeader
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.repository.UserRepository
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.MultipartBody
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

    override suspend fun getUserInfo(): User {
        return userApi.getUserInfo().body() ?: User()
    }

    override suspend fun patchUpdatePassword(email: String, password: String): String {
        return userApi.patchUpdatePassword(email, password).message()
    }

    override suspend fun patchUpdatePhotoUser(byteArray: ByteArray) {
        val requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), byteArray)
        val body = MultipartBody.Part.createFormData("file","user_photo",requestFile)
        userApi.patchUpdatePhotoUser(body)
    }
}