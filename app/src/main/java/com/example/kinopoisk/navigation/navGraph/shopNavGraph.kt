package com.example.kinopoisk.navigation.navGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kinopoisk.navigation.SHOP_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.shop.ShopAddFilmItemScreen
import com.example.kinopoisk.screen.shop.ShopScreen

fun NavGraphBuilder.shopNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = Screen.Shop.route,
        route = SHOP_ROUTE,
        builder = {
            composable(Screen.Shop.route){
                ShopScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope
                )
            }
            composable(Screen.ShopAddFilmItem.route){
                ShopAddFilmItemScreen(
                    navController = navController
                )
            }
        }
    )
}