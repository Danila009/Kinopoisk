package com.example.core_network_domain.model.movie

data class Similar(
    val total:Int? = null,
    val items:List<SimilarItem> = listOf()
)