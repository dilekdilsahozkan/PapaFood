package com.dilsahozkan.papafood.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilsahozkan.papafood.data.local.dao.FavoriteDao
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteLocalData: FavoriteDao
) : ViewModel() {

    private val _dataList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val dataList: StateFlow<List<FavoriteEntity>> = _dataList

    fun getAllRecipes() {
        viewModelScope.launch {
            favoriteLocalData.getAll().collect { recipes ->
                _dataList.value = recipes
            }
        }
    }

    fun deleteRecipe(recipe: FavoriteEntity) {
        viewModelScope.launch {
            favoriteLocalData.delete(recipe)
            getAllRecipes()
        }
    }
}