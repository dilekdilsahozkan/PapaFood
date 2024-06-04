package com.dilsahozkan.papafood.data.repository

import com.dilsahozkan.papafood.data.remote.api.Service
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val service: Service) {
    suspend fun getRandomRecipes() : Response<RandomRecipe> = service.randomRecipes(1, "", false, "1c9833c37b344ca1bc91dc4c9eb507ae")
}