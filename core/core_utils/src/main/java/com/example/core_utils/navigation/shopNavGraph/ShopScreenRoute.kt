package com.example.core_utils.navigation.shopNavGraph

sealed class ShopScreenRoute(val route:String){
    object Shop: ShopScreenRoute("shop_screen")
    object ShopAddFilmItem: ShopScreenRoute("shop_add_film_item_screen")
}
