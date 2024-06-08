package com.dilsahozkan.papafood.data.remote.model

data class SearchRecipe(
    var offset: Int? = null,
    var number: Int? = null,
    var results: List<Recipe>? = null
)