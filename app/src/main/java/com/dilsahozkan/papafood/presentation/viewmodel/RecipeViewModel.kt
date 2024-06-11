package com.dilsahozkan.papafood.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilsahozkan.papafood.common.BaseResult
import com.dilsahozkan.papafood.common.ViewState
import com.dilsahozkan.papafood.common.toLocal
import com.dilsahozkan.papafood.common.toNotification
import com.dilsahozkan.papafood.data.local.dao.FavoriteDao
import com.dilsahozkan.papafood.data.local.dao.NotificationDao
import com.dilsahozkan.papafood.data.local.dao.RecipeDao
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import com.dilsahozkan.papafood.domain.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase,
    private val localData: RecipeDao,
    private val favoriteLocalData: FavoriteDao,
    private val notificationDao: NotificationDao
) : ViewModel() {

    var _recipeState: MutableStateFlow<ViewState<RandomRecipe>> = MutableStateFlow(ViewState.Idle())
    val recipeState: StateFlow<ViewState<RandomRecipe>> = _recipeState

    var _detailState: MutableStateFlow<ViewState<RecipeDetail>> = MutableStateFlow(ViewState.Idle())
    val detailState: StateFlow<ViewState<RecipeDetail>> = _detailState

    var _searchState: MutableStateFlow<ViewState<SearchRecipe>> = MutableStateFlow(ViewState.Idle())
    val searchState: StateFlow<ViewState<SearchRecipe>> = _searchState

    var favoriteList = mutableListOf<Recipe>()

    var newRecipeTitles: MutableList<String> by mutableStateOf(mutableListOf())

    fun isFavorite(recipe: Recipe): Boolean {
        return favoriteList.contains(recipe)
    }


    fun getRandomRecipes() {
        viewModelScope.launch {
            recipeUseCase.getRandomRecipes()
                .onStart {
                    _recipeState.value = ViewState.Idle()
                }
                .catch { exception ->
                    _recipeState.value = ViewState.Error(message = exception.message)
                    Log.e("CATCH", "exception : $exception")
                }
                .collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            _recipeState.value = ViewState.Success(result.data)
                        }

                        is BaseResult.Error -> {
                            _recipeState.value = ViewState.Error()
                        }

                        else -> {}
                    }
                }
        }
    }

    fun getRecipeDetail(id: Int) {
        viewModelScope.launch {
            recipeUseCase.getRecipeDetail(id)
                .onStart {
                    _detailState.value = ViewState.Idle()
                }
                .catch { exception ->
                    _detailState.value = ViewState.Error(message = exception.message)
                    Log.e("CATCH", "exception : $exception")
                }
                .collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            _detailState.value = ViewState.Success(result.data)
                        }

                        is BaseResult.Error -> {
                            _detailState.value = ViewState.Error()
                        }

                        else -> {}
                    }
                }
        }
    }

    fun getSearch(searchText: String) {
        viewModelScope.launch {
            _searchState.value = ViewState.Loading()
            recipeUseCase.getAllRecipe(searchText)
                .onStart {
                    _searchState.value = ViewState.Idle()
                }
                .catch { exception ->
                    _searchState.value = ViewState.Error(message = exception.message)
                    Log.e("CATCH", "exception : $exception")
                }
                .collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            val newRecipes = result.data.toLocal()
                            val existingRecipeIds = localData.getAllRecipes().map { it.id }

                            val newUniqueRecipes =
                                newRecipes.filterNot { it.id in existingRecipeIds }

                            if (newUniqueRecipes.isNotEmpty()) {
                                localData.insertAll(newUniqueRecipes)

                                newRecipeTitles.apply {
                                    addAll(newUniqueRecipes.mapNotNull { it.title })
                                    if (size > 5) {
                                        removeAll(take(size - 5))
                                    }
                                }

                                notificationDao.insertAll(newUniqueRecipes.mapNotNull { it.toNotification() })
                            }

                            _searchState.value = ViewState.Success(result.data)
                        }

                        is BaseResult.Error -> {
                            _searchState.value = ViewState.Error()
                        }

                        else -> {}
                    }
                }
        }
    }

    fun addRecipeToFavorite(recipeList: List<Recipe>) {
        viewModelScope.launch {
            favoriteLocalData.deleteAll()
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
            favoriteLocalData.insertAll(favoriteEntityList)
        }
    }

    fun removeRecipeFromFavorite(recipe: Recipe) {
        viewModelScope.launch {
            favoriteLocalData.deleteAll()
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
            favoriteLocalData.insertAll(favoriteEntityList)
        }
        favoriteList.remove(recipe)
    }
}