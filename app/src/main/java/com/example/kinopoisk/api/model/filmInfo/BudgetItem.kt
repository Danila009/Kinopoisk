package com.example.kinopoisk.api.model.filmInfo

data class BudgetItem(
    val type:String,
    val amount:Int,
    val currencyCode:String,
    val name:String,
    val symbol:String
)