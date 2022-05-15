package com.example.core_network_domain.apiResponse

import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): com.example.core_network_domain.common.Response<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return com.example.core_network_domain.common.Response.Success(body)
                }
            }
            return com.example.core_network_domain.common.Response.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return com.example.core_network_domain.common.Response.Error(e.message.toString())
        }
    }
}