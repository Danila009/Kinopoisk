package com.example.kinopoisk.api.model.filmInfo.distribution

import com.example.kinopoisk.api.model.filmInfo.Countrie

data class DistributionItem(
    val type:String,
    val subType:String,
    val date:String,
    val reRelease:Boolean,
    val country:List<Countrie>,
    val companies:List<Companie>
)
