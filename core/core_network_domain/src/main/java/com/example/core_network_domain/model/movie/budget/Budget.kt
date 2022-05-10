package com.example.core_network_domain.model.movie.budget

data class Budget(
    val total:Int? = null,
    val items:List<BudgetItem> = listOf()
)