package com.example.kinopoisk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.kinopoisk.di.AppComponent
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.kinopoisk.navigation.host.BaseNavHost
import com.example.kinopoisk.ui.theme.KinopoiskTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private val appComponent:AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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