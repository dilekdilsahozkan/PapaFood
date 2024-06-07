package com.dilsahozkan.papafood.common

import com.dilsahozkan.papafood.data.local.entity.RecipeEntity
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe

fun SearchRecipe.toLocal(): List<RecipeEntity> {
    return this.results?.map {
        it.toLocal() ?: RecipeEntity(
            id = 0,
            title = "",
            image = "",
            summary = "",
            spoonacularScore = 0.0,
            readyInMinutes = 0,
            pricePerServing = 0.0,
            saved = false,
        )
    }.orEmpty()
}
fun Recipe.toLocal(): RecipeEntity {
    return RecipeEntity(
        id = this.id ?: 0,
        title = this.title ?: "",
        image = this.image ?: "",
        summary = this.summary ?: "",
        spoonacularScore = this.spoonacularScore ?: 0.0,
        readyInMinutes = this.readyInMinutes ?: 0,
        pricePerServing = this.pricePerServing ?: 0.0,
        saved = this.saved ?: false
    )
}