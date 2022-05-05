package com.example.feature_registration.common

import androidx.compose.runtime.MutableState

fun validateRegistration(
    username:String,
    email:String,
    password:String,
    message:MutableState<String>
):Boolean{

    if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
        message.value = "isEmpty"
        return false
    }

    if (email.length < 6 || password.length < 6 || username.length < 6){
        message.value = "length"
        return false
    }

    if (!email.any("." :: contains) || !email.any("@" :: contains)){
        message.value = "any contains"
        return false
    }

    return true
}