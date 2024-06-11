package com.dilsahozkan.papafood.common

import android.app.Notification
import com.dilsahozkan.papafood.data.local.entity.NotificationEntity
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe

fun SearchRecipe.toLocal(): List<RecipeEntity> {
    return this.results?.map {
        it.toLocal()
    }.orEmpty()
}
fun Recipe.toLocal(): RecipeEntity {
    return RecipeEntity(
        id = this.id ?: 0,
        title = this.title ?: "",
        image = this.image ?: "",
        summary = this.summary ?: "",
        score = this.score ?: 0.0,
        readyInMinutes = this.readyInMinutes ?: 0,
        pricePerServing = this.pricePerServing ?: 0.0,
        saved = this.saved ?: false
    )
}

fun RecipeEntity.toNotification(): NotificationEntity? {
    return if (this.title.isNullOrBlank()) {
        NotificationEntity(title = this.title.toString())
    } else {
        null
    }
}