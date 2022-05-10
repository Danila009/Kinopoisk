package com.example.core_network_domain.model.movie.distribution

data class Distribution(
    val total:Int? = null,
    val items:List<DistributionItem> = listOf()
)
