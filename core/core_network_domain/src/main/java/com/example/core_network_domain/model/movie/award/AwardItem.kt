package com.example.core_network_domain.model.movie.award


import com.example.core_network_domain.model.person.PersonItem
import kotlinx.serialization.Serializable

@Serializable
data class AwardItem(
    val imageUrl: String?,
    val name: String,
    val nominationName: String,
    val win: Boolean,
    val year: Int,
    val persons:List<PersonItem>?
)