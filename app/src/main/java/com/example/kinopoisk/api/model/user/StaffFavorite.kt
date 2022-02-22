package com.example.kinopoisk.api.model.user


import com.google.gson.annotations.SerializedName

data class StaffFavorite(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nameEn")
    val nameEn: String?,
    @SerializedName("nameRu")
    val nameRu: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("professionKey")
    val professionKey: String?,
    @SerializedName("professionText")
    val professionText: String?,
    @SerializedName("staffId")
    val staffId: Int?
)