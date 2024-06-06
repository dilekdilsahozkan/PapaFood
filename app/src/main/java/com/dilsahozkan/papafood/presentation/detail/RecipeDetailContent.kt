package com.dilsahozkan.papafood.presentation.detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.common.ViewState
import com.dilsahozkan.papafood.common.stripHtml
import com.dilsahozkan.papafood.data.remote.model.Ingredients
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.presentation.homePage.RecipeViewModel
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.mediumFont
import com.dilsahozkan.papafood.ui.theme.regular

@SuppressLint("DefaultLocale")
@Composable
fun RecipeDetailContent(
    recipe: RecipeDetail,
    scrollState: LazyListState,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val uiState by viewModel.recipeState.collectAsState()
    val formattedScore = String.format("%.1f", recipe.spoonacularScore)

    LazyColumn(contentPadding = PaddingValues(top = 350.dp), state = scrollState) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                InfoColumn(
                    iconResource = R.drawable.ic_time,
                    text = recipe.readyInMinutes.toString() + " minutes"
                )
                InfoColumn(
                    iconResource = R.drawable.ic_star,
                    text = formattedScore
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = "Ingredients",
                color = MainColor,
                fontSize = 20.sp,
                fontFamily = mediumFont,
                textAlign = TextAlign.Start
            )

            if (uiState is ViewState.Success) {
                val recipeDetail: List<Ingredients> =
                    (uiState as ViewState.Success<RecipeDetail>).data.extendedIngredients
                        ?: emptyList()
                LazyColumn {
                    items(recipeDetail) { ingredient ->
                        IngredientCard(ingredients = ingredient, modifier = Modifier)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Construction",
                    color = MainColor,
                    fontSize = 20.sp,
                    fontFamily = mediumFont,
                    textAlign = TextAlign.Start,
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    )
                ) {
                    recipe.instructions?.let {
                        Text(
                            text = it.stripHtml(),
                            textAlign = TextAlign.Start,
                            fontFamily = regular
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientCard(
    ingredients: Ingredients,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(MainColor),
        modifier = modifier
            .padding(bottom = 16.dp, start = 4.dp, end = 4.dp)
            .aspectRatio(1f)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = ingredients.name.toString(),
                fontSize = 13.sp,
                fontFamily = mediumFont,
                textAlign = TextAlign.Center,
                maxLines = if (expanded) Int.MAX_VALUE else 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun InfoColumn(@DrawableRes iconResource: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            tint = MainColor,
            modifier = Modifier
                .size(30.dp)
                .padding(bottom = 5.dp)
        )
        Text(text = text, fontFamily = mediumFont)
    }
}
