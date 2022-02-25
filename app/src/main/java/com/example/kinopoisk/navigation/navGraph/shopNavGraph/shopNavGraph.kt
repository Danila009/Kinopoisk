package com.example.kinopoisk.navigation.navGraph.shopNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kinopoisk.navigation.navGraph.shopNavGraph.constants.ShopScreenConstants.Route.SHOP_ROUTE
import com.example.kinopoisk.navigation.navGraph.shopNavGraph.constants.ShopScreenRoute
import com.example.kinopoisk.screen.shop.ShopAddFilmItemScreen
import com.example.kinopoisk.screen.shop.ShopScreen

fun NavGraphBuilder.shopNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = ShopScreenRoute.Shop.route,
        route = SHOP_ROUTE,
        builder = {
            composable(ShopScreenRoute.Shop.route){
                ShopScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope
                )
            }
            composable(ShopScreenRoute.ShopAddFilmItem.route){
                ShopAddFilmItemScreen(
                    navController = navController
                )
            }
        }
    )
}