package com.example.core_network_domain.model.rickAndMorty

import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeItem(
    val id:Int,
    val name:String,
    val air_date:String,
    val episode:String,
    val url:String,
    @Serializable(with = DateTimeSerialization::class)
    val created:String
)