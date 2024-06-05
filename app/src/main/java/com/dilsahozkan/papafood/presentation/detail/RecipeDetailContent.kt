package com.dilsahozkan.papafood.presentation.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.SoftOrangeColor
import com.dilsahozkan.papafood.ui.theme.mediumFont

@Composable
fun RecipeDetailContent(recipe: RecipeDetail, scrollState: LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = 350.dp), state = scrollState) {
        item {
            BasicInfo(recipe)
            IngredientsHeader()
            IngredientsList(recipe)
            Steps(recipe)
        }
    }
}

@Composable
fun BasicInfo(recipe: RecipeDetail) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
//        InfoColumn(
//            iconResource = R.drawable.ic_time,
//            text =  if (recipe.totalTime.isNullOrBlank()) "-" else recipe.makingAmount.toString())
//        InfoColumn(
//            iconResource = R.drawable.ic_star,
//            text =  if (recipe.makingAmount.isNullOrBlank()) "-" else recipe.makingAmount.toString())
    }
}

@Composable
fun IngredientsHeader() {
    val medium: CornerBasedShape = RoundedCornerShape(4.dp)

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clip(medium)
            .background(White)
    ) {
        Text(
            text = "Ingredients",
            color = MainColor,
            fontSize = 20.sp,
            fontFamily = mediumFont,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun Steps(recipe: RecipeDetail) {

    Column(
        modifier = Modifier.fillMaxHeight()
            .padding(horizontal = 16.dp)
            .background(White),
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
                .padding(top =16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
//            Text(
//                text = recipe.directions.toString(),
//                textAlign = TextAlign.Start,
//                fontFamily = regular
//            )
        }
    }
}

@Composable
fun IngredientsList(recipe: RecipeDetail) {
//    EasyGrid(nColumns = 3, items = recipe.ingredients ?: emptyList()) {
//        IngredientCard(it, Modifier)
//    }
}

@Composable
fun IngredientCard(
    name: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(MainColor),
        modifier = modifier
            .padding(bottom = 16.dp, start = 4.dp, end = 4.dp)
            .aspectRatio(1f)
            .background(White)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = name,
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
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
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
            tint = SoftOrangeColor,
            modifier = Modifier.height(50.dp)
        )
        Text(text = text, fontFamily = mediumFont)
    }
}
