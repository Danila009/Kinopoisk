package com.example.kinopoisk.navigation.navGraph.shopNavGraph.constants

sealed class ShopScreenRoute(val route:String){
    object Shop: ShopScreenRoute("shop_screen")
    object ShopAddFilmItem: ShopScreenRoute("shop_add_film_item_screen")
}
