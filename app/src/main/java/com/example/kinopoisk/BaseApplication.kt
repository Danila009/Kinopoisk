package com.example.kinopoisk

import android.app.Application
import com.example.core_utils.common.BaseConstants
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        val options = FirebaseOptions.Builder()
            .setProjectId(BaseConstants.PROJECT_ID)
            .setApplicationId(BaseConstants.APPLICATION_ID)
            .setApiKey(BaseConstants.API_KEY)
            .build()

        Firebase.initialize(this, options, BaseConstants.PROJECT_ID)
    }
}