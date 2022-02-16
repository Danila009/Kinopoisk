package com.example.kinopoisk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.kinopoisk.navigation.host.Host
import com.example.kinopoisk.ui.theme.KinopoiskTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
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