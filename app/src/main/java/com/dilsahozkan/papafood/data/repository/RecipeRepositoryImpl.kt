package com.dilsahozkan.papafood.data.repository

import com.dilsahozkan.papafood.common.PreferencesManager
import com.dilsahozkan.papafood.data.remote.api.Service
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import retrofit2.Response
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val service: Service,
    private val preferencesManager: PreferencesManager
) : RecipeRepository {

    val apiKey = preferencesManager.getApiKey()

    override suspend fun getRandomRecipes(): Response<RandomRecipe> =
        service.randomRecipes(4, "", false, apiKey)

    override suspend fun getRecipeDetail(id: Int): Response<RecipeDetail> =
        service.recipeDetail(id, apiKey)

    override suspend fun getRecipeSearch(searchText: String): Response<SearchRecipe> =
        service.allFoods(searchText, apiKey)

}