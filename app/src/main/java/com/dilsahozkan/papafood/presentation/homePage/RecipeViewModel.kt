package com.dilsahozkan.papafood.presentation.homePage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilsahozkan.papafood.common.BaseResult
import com.dilsahozkan.papafood.common.ViewState
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.domain.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeUseCase: RecipeUseCase) : ViewModel()  {

    var _recipeState: MutableStateFlow<ViewState<RandomRecipe>> = MutableStateFlow(ViewState.Idle())
    val recipeState: StateFlow<ViewState<RandomRecipe>> = _recipeState

    var _detailState: MutableStateFlow<ViewState<RecipeDetail>> = MutableStateFlow(ViewState.Idle())
    val detailState: StateFlow<ViewState<RecipeDetail>> = _detailState

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
}