package com.dilsahozkan.papafood.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilsahozkan.papafood.data.local.dao.FavoriteDao
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import com.dilsahozkan.papafood.data.remote.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val localData: FavoriteDao
) : ViewModel() {

    private val _dataList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val dataList: StateFlow<List<FavoriteEntity>> = _dataList

    var favoriteList = mutableListOf<Recipe>()

    fun isFavorite(recipe: Recipe): Boolean {
        return favoriteList.contains(recipe)
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            localData.getAll().collect { recipes ->
                _dataList.value = recipes
            }
        }
    }

    fun deleteRecipe(recipe: FavoriteEntity) {
        viewModelScope.launch {
            localData.delete(recipe)
        }
    }

    fun addRecipeToFavorite(recipeList: List<Recipe>) {
        viewModelScope.launch {
            localData.deleteAll()
            val favoriteEntityList = recipeList.map {
                FavoriteEntity(
                    id = it.id,
                    title = it.title,
                    image = it.image,
                    summary = it.summary,
                    score = it.score,
                    readyInMinutes = it.readyInMinutes,
                    pricePerServing = it.pricePerServing,
                    saved = false
                )
            }
            localData.insertAll(favoriteEntityList)
        }
    }

    fun removeRecipeFromFavorite(recipe: Recipe) {
        viewModelScope.launch {
            localData.deleteAll()
            val favoriteEntityList = favoriteList.map {
                FavoriteEntity(
                    title = it.title,
                    image = it.image,
                    summary = it.summary,
                    score = it.score,
                    readyInMinutes = it.readyInMinutes,
                    pricePerServing = it.pricePerServing,
                    saved = false
                )
            }
            localData.insertAll(favoriteEntityList)
        }
        favoriteList.remove(recipe)
    }
}