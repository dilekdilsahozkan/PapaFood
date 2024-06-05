package com.dilsahozkan.papafood.data.remote.model

data class RecipeDetail(
    val id: Int? = null,
    var title: String? = null,
    var image: String? = null,
    var spoonacularScore: Double? = null,
    var readyInMinutes: Int? = null,
    var extendedIngredients: List<Ingredients>? = null,
    var summary: String? = null,
    var pricePerServing: String? = null,
    var instructions: String? = null
)

data class Ingredients(
    var name: String? = null,
    var image: String? = null
)