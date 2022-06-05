package com.example.core_network_domain.model.movie.budget

data class BudgetItem(
    val type:String,
    val amount:Int?,
    val currencyCode:String,
    val name:String,
    val symbol:String
)