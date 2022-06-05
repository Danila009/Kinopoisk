package com.example.core_network_domain.model.movie.trailer

data class Trailer(
    val total:Int,
    val items:List<TrailerItem>
)

data class TrailerItem(
    val url:String,
    val name:String,
    val site: TrailerSite
)

enum class TrailerSite{
    UNKNOWN,
    YOUTUBE
}