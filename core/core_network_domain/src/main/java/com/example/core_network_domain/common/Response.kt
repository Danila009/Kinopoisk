package com.example.core_network_domain.common

sealed class Response<T>(val message:String? = null, val data:T? = null){
    class Error<T>(message: String):Response<T>(message = message)
    class Loading<T>:Response<T>()
    class Success<T>(data: T):Response<T>(data = data)
}
