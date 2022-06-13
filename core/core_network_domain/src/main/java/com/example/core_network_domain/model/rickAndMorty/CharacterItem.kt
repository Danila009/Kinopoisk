package com.example.core_network_domain.model.rickAndMorty

import com.example.core_network_domain.enums.StatusCharacterRickAndMorty
import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.Serializable

@Serializable
data class CharacterItem(
    val id:Int,
    val name:String,
    val status: StatusCharacterRickAndMorty,
    val species:String,
    val type:String,
    val gender:String,
    val image:String,
    val url:String,
    @Serializable(with = DateTimeSerialization::class)
    val created:String,
    val origin: Origin? = null,
    val location: LocationCharacterById? = null,
    val episode:List<String>? = null
)