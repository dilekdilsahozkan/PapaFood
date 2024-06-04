package com.dilsahozkan.papafood.data.remote.model

import com.google.gson.annotations.SerializedName

data class RandomRecipe(
    @SerializedName("recipes") var recipes: List<Recipe>? = null
)