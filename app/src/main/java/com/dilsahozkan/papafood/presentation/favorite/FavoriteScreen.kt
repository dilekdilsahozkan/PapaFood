package com.dilsahozkan.papafood.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.dilsahozkan.papafood.ui.theme.mediumFont

@Composable
fun FavoriteScreen(
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

        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(minSize = 205.dp)

        ) {
            items(dataList) { item ->
                FavoriteItemScreen(item)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllRecipes()
    }
}