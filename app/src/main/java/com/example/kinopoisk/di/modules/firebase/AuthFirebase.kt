package com.example.kinopoisk.di.modules.firebase

import com.example.core_utils.common.Constants.PROJECT_ID
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthFirebase {

    @[Provides Singleton]
    fun providerSecondary(): FirebaseApp = Firebase.app(PROJECT_ID)

    @[Provides Singleton]
    fun providerFirebaseAuth(
        firebaseApp: FirebaseApp
    ):FirebaseAuth = Firebase.auth(firebaseApp)
}