package com.example.kinopoisk.screen.login.validate

import android.content.Context
import android.widget.Toast

fun validateAuthorization(
    email:String,
    password:String,
    context: Context
):Boolean{

    if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "isEmpty", Toast.LENGTH_SHORT).show()
        return false
    }

    if (email.length < 6 || password.length < 6){
        Toast.makeText(context, "length", Toast.LENGTH_SHORT).show()
        return false
    }

    if (!email.any("." :: contains) || !email.any("." :: contains)){
        Toast.makeText(context, "any contains", Toast.LENGTH_SHORT).show()
        return false
    }

    return true
}


