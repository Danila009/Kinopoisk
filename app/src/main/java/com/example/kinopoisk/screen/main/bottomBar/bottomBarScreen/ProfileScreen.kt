package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.ProfileView
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.TokenNullLoginView
import com.example.kinopoisk.utils.Constants.TOKEN

@Composable
fun ProfileScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    val context = LocalContext.current

    val token = context
        .getSharedPreferences(TOKEN, Context.MODE_PRIVATE)
        .getString(TOKEN, "")

    if (token!!.isEmpty()){
        TokenNullLoginView(
            navController = navController
        )
    }else{
        ProfileView(
            lifecycleScope = lifecycleScope,
            navController = navController
        )
    }
}