package com.example.kinopoisk.api.model.filmInfo

data class Budget(
    val total:Int? = null,
    val items:List<BudgetItem> = listOf()
)