package com.dilsahozkan.papafood.data.remote.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    val id: Int,
    var title: String? = null,
    var image: String? = null,
    var summary: String? = null,
    @SerializedName("spoonacularScore") val score: Double? = null,
    var pricePerServing: Double? = null,
    var readyInMinutes: Int? = null,
    var saved: Boolean? = null
)