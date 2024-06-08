package com.dilsahozkan.papafood.data.remote.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("spoonacularScore") val spoonacularScore: Double? = null,
    @SerializedName("pricePerServing") val pricePerServing: Double? = null,
    @SerializedName("readyInMinutes") val readyInMinutes: Int? = null,
    @SerializedName("saved") val saved: Boolean? = null
)