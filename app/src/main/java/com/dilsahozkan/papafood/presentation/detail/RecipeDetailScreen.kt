package com.dilsahozkan.papafood.presentation.detail

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilsahozkan.papafood.presentation.homePage.RecipeViewModel
@Composable
fun RecipeDetailScree(
    navController: NavController,
    recipeId: Int?,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val scrollState = rememberLazyListState()
  //  val detailState by viewModel.detailState.collectAsStateWithLifecycle()

}