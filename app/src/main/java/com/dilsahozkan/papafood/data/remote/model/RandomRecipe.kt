package com.dilsahozkan.papafood.data.remote.model

import com.google.gson.annotations.SerializedName

data class RandomRecipe(
    val recipes: List<Recipe>? = null
)