package com.dilsahozkan.papafood.presentation.favorite

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilsahozkan.papafood.ui.theme.mediumFont

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val dataList by viewModel.dataList.collectAsState()

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 5.dp),
                text = "Recipe Saved 🍩",
                fontSize = 20.sp,
                fontFamily = mediumFont
            )
        }
    ) { paddingValues ->

            LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxWidth()

        ) {
            items(dataList) { item ->
                FavoriteItemScreen(item, navController)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllRecipes()
    }
}