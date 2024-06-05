package com.dilsahozkan.papafood.presentation.homePage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.common.PageIndicator
import com.dilsahozkan.papafood.common.ViewState
import com.dilsahozkan.papafood.data.remote.model.RandomRecipe
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.ui.theme.mediumFont

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val uiState by viewModel.recipeState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRandomRecipes()
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome 👋🏼",
                    fontSize = 20.sp,
                    fontFamily = mediumFont
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Notification"
                )
            }
        }
    ) { paddingValues ->

        Column {

            if (uiState is ViewState.Success) {
                val recipes: List<Recipe> =
                    (uiState as ViewState.Success<RandomRecipe>).data.recipes ?: emptyList()
                val imageSlider = listOf(recipes)
                val pagerState = rememberPagerState(pageCount = { imageSlider.size})

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    items(recipes) { recipe ->

                        HorizontalPager(
                            state = pagerState,
                            pageSpacing = 16.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            RecipeSliderScreen(recipe = recipe, navController = navController)
                        }
                        PageIndicator(
                            pageCount = recipes.size,
                            currentPage = pagerState.currentPage,
                            modifier = Modifier
                        )
                    }
                }
                RecipeItemScreen(modifier = Modifier
                    .fillMaxWidth())
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
 //   HomeScreen()
}
