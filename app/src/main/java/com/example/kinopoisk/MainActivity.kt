package com.example.kinopoisk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.core_utils.common.Constants
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.kinopoisk.navigation.host.BaseNavHost
import com.example.kinopoisk.ui.theme.KinopoiskTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
class MainActivity: ComponentActivity() {

    private val appComponent:AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val options = FirebaseOptions.Builder()
                .setProjectId(Constants.PROJECT_ID)
                .setApplicationId(Constants.APPLICATION_ID)
                .setApiKey(Constants.API_KEY)
                .build()

            Firebase.initialize(this, options, Constants.PROJECT_ID)

            KinopoiskTheme {
                BaseNavHost(
                    navHostController = rememberNavController(),
                    lifecycleScope = lifecycleScope,
                    appComponent = appComponent
                )
            }
        }
    }
}