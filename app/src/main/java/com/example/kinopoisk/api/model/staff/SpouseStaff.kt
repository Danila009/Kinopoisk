package com.example.kinopoisk.api.model.staff


import com.google.gson.annotations.SerializedName

data class SpouseStaff(
    @SerializedName("children")
    val children: Int?,
    @SerializedName("divorced")
    val divorced: Boolean?,
    @SerializedName("divorcedReason")
    val divorcedReason: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("personId")
    val personId: Int?,
    @SerializedName("relation")
    val relation: String?,
    @SerializedName("sex")
    val sex: String?,
    @SerializedName("webUrl")
    val webUrl: String?
)