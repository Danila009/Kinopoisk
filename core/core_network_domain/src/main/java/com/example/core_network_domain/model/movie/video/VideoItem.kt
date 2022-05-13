package com.example.core_network_domain.model.movie.video

import kotlinx.serialization.Serializable

@Serializable
data class VideoItem(
    val id:Int,
    val kinoPoiskId:Int,
    val title:String,
    val date:String,
    val videoUrl:String,
    val torrentUrl:String,
    val downloadUrl:String
)
