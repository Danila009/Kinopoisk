package com.example.kinopoisk

import android.app.Application
import com.example.core_utils.common.Constants
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        val options = FirebaseOptions.Builder()
            .setProjectId(Constants.PROJECT_ID)
            .setApplicationId(Constants.APPLICATION_ID)
            .setApiKey(Constants.API_KEY)
            .build()

        Firebase.initialize(this, options, Constants.PROJECT_ID)
    }
}