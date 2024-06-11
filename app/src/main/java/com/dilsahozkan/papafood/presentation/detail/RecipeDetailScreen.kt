package com.dilsahozkan.papafood.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilsahozkan.papafood.common.ViewState
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.presentation.viewmodel.RecipeViewModel
@Composable
fun RecipeDetailScreen(
    navController: NavController,
    recipeId: Int?,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val scrollState = rememberLazyListState()
    val detailState by viewModel.detailState.collectAsState()

    LaunchedEffect(recipeId) {
        recipeId?.let { viewModel.getRecipeDetail(it) }
    }

    Box(modifier = Modifier.background(Color.White)) {
        if (detailState is ViewState.Success) {
            val recipeDetail: RecipeDetail =
                (detailState as ViewState.Success<RecipeDetail>).data

            RecipeDetailContent(recipeDetail, scrollState)
            RecipeDetailTopBarScreen(recipeDetail, scrollState, navController)
        }
    }
}