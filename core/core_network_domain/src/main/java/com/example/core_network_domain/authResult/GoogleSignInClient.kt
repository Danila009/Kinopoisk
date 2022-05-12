package com.example.core_network_domain.authResult

import android.app.Activity
import android.content.Context
import com.example.core_network_domain.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    context as Activity
    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.webclient_id))
//        .requestIdToken(CLIENT_ID_DOMAIN)
//        .requestIdToken(Constants.CLIENT_ID_REGISTRATION)
        .requestEmail()
        .requestProfile()
        .build()

    return GoogleSignIn.getClient(context, options)
}