package com.example.kinopoisk.api.model.staff


import com.google.gson.annotations.SerializedName

data class FilmStaff(
    @SerializedName("description")
    val description: String?,
    @SerializedName("filmId")
    val filmId: Int = 0,
    @SerializedName("general")
    val general: Boolean?,
    @SerializedName("nameEn")
    val nameEn: String?,
    @SerializedName("nameRu")
    val nameRu: String?,
    @SerializedName("professionKey")
    val professionKey: String?,
    @SerializedName("rating")
    val rating: String? = null
)