package com.example.core_network_domain.model.movie

data class Filter(
    val genres:List<Genre> = listOf(),
    val countries:List<Countrie> = listOf()
)
