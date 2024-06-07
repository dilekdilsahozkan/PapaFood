package com.dilsahozkan.papafood.domain

import com.dilsahozkan.papafood.common.BaseResult
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import com.dilsahozkan.papafood.data.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {

    fun getRandomRecipes(): Flow<BaseResult<RandomRecipe>> {
        return flow {
            val value = recipeRepository.getRandomRecipes()

            if (value.isSuccessful && value.code() == 200) {
                emit(
                    BaseResult.Success(
                        value.body() ?: RandomRecipe()
                    )
                )
            }
        }
    }

    fun getRecipeDetail(id: Int): Flow<BaseResult<RecipeDetail>> {
        return flow {
            val value = recipeRepository.getRecipeDetail(id)

            if (value.isSuccessful && value.code() == 200) {
                emit(
                    BaseResult.Success(
                        value.body() ?: RecipeDetail()
                    )
                )
            }
        }
    }

    fun getAllRecipe(searchText: String): Flow<BaseResult<SearchRecipe>> {
        return flow {
            val value = recipeRepository.getRecipeSearch(searchText)

            if (value.isSuccessful && value.code() == 200) {
                emit(
                    BaseResult.Success(
                        value.body() ?: SearchRecipe()
                    )
                )
            }
        }
    }

//    fun insertRecipe(isSaved: Recipe): Flow<BaseResult<Recipe>> {
//        return flow {
//            emit(
//                BaseResult.Success(
//                    recipeRepository.addFavorites(isSaved)
//                )
//            )
//        }
//    }
}