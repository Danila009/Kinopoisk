package com.example.kinopoisk.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class Notification:FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("token", token)
    }
}