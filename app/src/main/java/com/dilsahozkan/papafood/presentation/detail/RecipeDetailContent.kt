package com.dilsahozkan.papafood.presentation.detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.common.stripHtml
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.mediumFont
import com.dilsahozkan.papafood.ui.theme.regular

@SuppressLint("DefaultLocale")
@Composable
fun RecipeDetailContent(
    recipe: RecipeDetail,
    scrollState: LazyListState
) {

    val ingredientsList = recipe.extendedIngredients ?: emptyList()
    val formattedScore = String.format("%.1f", recipe.score)

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

            Box(modifier = Modifier.height(230.dp)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(ingredientsList) { ingredient ->
                        IngredientItem(ingredients = ingredient)
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
                            color = Black,
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
        Text(
            text = text, fontFamily = mediumFont,
            color = Black
        )
    }
}