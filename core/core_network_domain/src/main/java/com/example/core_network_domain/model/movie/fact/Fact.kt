package com.example.core_network_domain.model.movie.fact

data class Fact(
    val total:Int? = null,
    val items:List<FactItem> = listOf()
)