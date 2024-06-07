package com.dilsahozkan.papafood.presentation.homePage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.mediumFont

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val uiState by viewModel.recipeState.collectAsState()
    val searchState by viewModel.searchState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRandomRecipes()
        viewModel.getSearch("")
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

            if (uiState is ViewState.Success && searchState is ViewState.Success) {
                val randomRecipes: List<Recipe> = (uiState as ViewState.Success<RandomRecipe>).data.recipes ?: emptyList()
                val recipeList = (searchState as ViewState.Success<SearchRecipe>).data.results ?: emptyList()
                val pagerState = rememberPagerState(pageCount = { randomRecipes.size })

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    item {
                        HorizontalPager(
                            state = pagerState,
                            contentPadding = PaddingValues( end = 80.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .width(280.dp)// Adjust height as needed
                        ) { page ->

                                RecipeSliderScreen(
                                    recipe = randomRecipes[page],
                                    navController = navController
                                )

                        }
                        PageIndicator(
                            pageCount = randomRecipes.size,
                            currentPage = pagerState.currentPage,
                            modifier = Modifier
                        )
                    }
                    items(recipeList) { recipe ->
                        RecipeItemScreen(
                            recipe = recipe,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    //   HomeScreen()
}
