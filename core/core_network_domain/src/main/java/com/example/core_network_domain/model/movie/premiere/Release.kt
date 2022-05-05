package com.example.core_network_domain.model.movie.premiere

data class Release(
    val page:Int,
    val total:Int,
    val releases:List<ReleaseItem>
)