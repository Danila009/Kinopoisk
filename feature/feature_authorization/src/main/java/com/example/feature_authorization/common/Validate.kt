package com.example.feature_authorization.common

import androidx.compose.runtime.MutableState

fun validateAuthorization(
    email:String,
    password:String,
    message:MutableState<String>
):Boolean{

    if (email.isEmpty() || password.isEmpty()) {
        message.value = "isEmpty"
        return false
    }

    if (email.length < 6 || password.length < 6){
        message.value = "length"
        return false
    }

    if (!email.any("." :: contains) || !email.any("." :: contains)){
        message.value = "any contains"
        return false
    }

    return true
}