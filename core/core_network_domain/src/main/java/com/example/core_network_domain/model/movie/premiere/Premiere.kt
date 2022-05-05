package com.example.core_network_domain.model.movie.premiere

data class Premiere(
    val total:Int? = null,
    val items:List<PremiereItem> = listOf()
)