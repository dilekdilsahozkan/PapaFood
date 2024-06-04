package com.dilsahozkan.papafood.data.remote.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("spoonacularScore") val spoonacularScore: Double?,
    @SerializedName("readyInMinutes") val readyInMinutes: Int?
)