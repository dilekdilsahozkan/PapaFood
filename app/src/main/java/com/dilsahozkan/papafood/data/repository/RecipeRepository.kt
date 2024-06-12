package com.dilsahozkan.papafood.data.repository

import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import retrofit2.Response

interface RecipeRepository {
    suspend fun getRandomRecipes(): Response<RandomRecipe>
    suspend fun getRecipeDetail(id: Int): Response<RecipeDetail>
    suspend fun getRecipeSearch(searchText: String): Response<SearchRecipe>
}