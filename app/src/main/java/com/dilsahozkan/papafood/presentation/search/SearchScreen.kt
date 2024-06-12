package com.dilsahozkan.papafood.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.common.ViewState
import com.dilsahozkan.papafood.data.remote.model.SearchRecipe
import com.dilsahozkan.papafood.presentation.viewmodel.RecipeViewModel
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.mediumFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val searchState by viewModel.searchState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSearch("")
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = stringResource(id = R.string.search),
                    fontSize = 20.sp,
                    fontFamily = mediumFont
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextField(
                value = searchText,
                onValueChange = { viewModel.onSearchTextChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_for),
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 10.dp),
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(id = R.string.search),
                        tint = Gray
                    )
                },

                shape = CircleShape,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (searchState is ViewState.Success) {
                        val searchList =
                            (searchState as ViewState.Success<SearchRecipe>).data.results
                                ?: emptyList()

                        items(searchList) { recipe ->
                            SearchItem(recipe) {
                                navController.navigate("recipe_detail/${recipe.id}")
                            }
                        }

                    }
                }
            }
        }
    }
}