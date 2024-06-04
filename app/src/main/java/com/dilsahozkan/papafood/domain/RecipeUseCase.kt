package com.dilsahozkan.papafood.domain

import com.dilsahozkan.papafood.common.BaseResult
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
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
}