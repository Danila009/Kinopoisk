package com.example.core_network_domain.model.movie.distribution

import com.example.core_network_domain.model.movie.Countrie

data class DistributionItem(
    val type:String,
    val subType:String,
    val date:String,
    val reRelease:Boolean,
    val country: Countrie? = null,
    val companies:List<Companie>
)
