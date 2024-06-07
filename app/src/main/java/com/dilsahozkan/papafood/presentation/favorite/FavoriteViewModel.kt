package com.dilsahozkan.papafood.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilsahozkan.papafood.data.local.dao.FavoriteDao
import com.dilsahozkan.papafood.data.local.dao.RecipeDao
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteLocalData: FavoriteDao
) : ViewModel()  {

    private val _dataList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val dataList: StateFlow<List<FavoriteEntity>> = _dataList

    fun getAllRecipes() {
        viewModelScope.launch {
            favoriteLocalData.getAll().collect { recipes ->
                _dataList.value = recipes
            }
        }
    }
}