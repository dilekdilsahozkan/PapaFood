package com.dilsahozkan.papafood.data.repository

import com.dilsahozkan.papafood.data.local.dao.RecipeDao
import com.dilsahozkan.papafood.data.remote.api.Service
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val service: Service,
    private val localDataSource: RecipeDao

) {

    suspend fun getRandomRecipes(): Response<RandomRecipe> =
        service.randomRecipes(3, "", false, "1c9833c37b344ca1bc91dc4c9eb507ae")

    suspend fun getRecipeDetail(id: Int): Response<RecipeDetail> =
        service.recipeDetail(id, "1c9833c37b344ca1bc91dc4c9eb507ae")

    suspend fun getRecipeSearch(searchText :String): Response<SearchRecipe> =
        service.allFoods(searchText,"9994454bfd3d4a3190e102bb6d88e3e4")

}