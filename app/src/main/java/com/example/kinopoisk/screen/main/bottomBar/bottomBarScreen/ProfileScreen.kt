package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.ProfileView
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.TokenNullLoginView
import com.example.kinopoisk.utils.Constants

@Composable
fun ProfileScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    val context = LocalContext.current

    val token = context
        .getSharedPreferences(Constants.TOKEN_SHARED, Context.MODE_PRIVATE)
        .getString(Constants.TOKEN_SHARED, "")

    if (token!!.isNotEmpty()){
        ProfileView(
            lifecycleScope = lifecycleScope,
            navController = navController
        )
    }else{
        TokenNullLoginView(
            navController = navController
        )
    }
}