package com.dilsahozkan.papafood.presentation.homePage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.dilsahozkan.papafood.presentation.viewmodel.RecipeViewModel
import com.dilsahozkan.papafood.ui.theme.mediumFont
import com.google.gson.Gson

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val recipeSliderState by viewModel.recipeState.collectAsState()
    val recipeItemState by viewModel.searchState.collectAsState()

    val mealTypes = listOf(
        "Main Course",
        "Dessert",
        "Appetizer",
        "Salad",
        "Bread",
        "Breakfast",
        "Soup",
        "Beverage",
    )

    LaunchedEffect(Unit) {
        viewModel.getRandomRecipes()
        viewModel.getSearch("Main Course")
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    fontSize = 20.sp,
                    fontFamily = mediumFont
                )

                IconButton(
                    onClick = {
                        val titlesJson = Gson().toJson(viewModel.newRecipeTitles)
                        navController.navigate("notifications?titles=$titlesJson")
                    },
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_new_notification),
                        contentDescription = "Notification"
                    )
                }
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (recipeSliderState is ViewState.Success) {
                val randomRecipes: List<Recipe> =
                    (recipeSliderState as ViewState.Success<RandomRecipe>).data.recipes
                        ?: emptyList()
                item {
                    val pagerState = rememberPagerState(pageCount = { randomRecipes.size })
                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(end = 80.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .width(280.dp)
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
            }

            if (recipeItemState is ViewState.Success) {
                val recipeList =
                    (recipeItemState as ViewState.Success<SearchRecipe>).data.results ?: emptyList()

                item {
                    LazyRow(
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(mealTypes) { mealTypes ->
                            CategoryItem(
                                modifier = Modifier,
                                category = mealTypes,
                                onItemClick = { category ->
                                    viewModel.getSearch(category)
                                },
                            )
                        }
                    }
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