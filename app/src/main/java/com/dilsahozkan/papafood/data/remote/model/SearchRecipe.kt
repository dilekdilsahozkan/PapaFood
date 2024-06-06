package com.dilsahozkan.papafood.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchRecipe(
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("number") var number: Int? = null,
    @SerializedName("results") var results: List<Recipe>? = null
)