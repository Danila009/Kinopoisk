package com.example.kinopoisk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.kinopoisk.navigation.navGraph.host.Host
import com.example.kinopoisk.ui.theme.KinopoiskTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinopoiskTheme {
                Host(
                    navHostController = rememberNavController(),
                    lifecycleScope = lifecycleScope
                )
            }
        }
    }
}