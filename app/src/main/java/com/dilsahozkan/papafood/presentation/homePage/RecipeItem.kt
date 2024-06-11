package com.dilsahozkan.papafood.presentation.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.presentation.viewmodel.RecipeViewModel
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.semiBold

@SuppressLint("DefaultLocale")
@Composable
fun RecipeItemScreen(
    recipe: Recipe,
    modifier: Modifier,
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val isFavorite = remember { mutableStateOf(viewModel.isFavorite(recipe)) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .height(265.dp)
            .padding(top = 16.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navController.navigate("recipe_detail/${recipe.id}")
            }
            .fillMaxWidth()
    ) {
        Column {

            Box(
                contentAlignment = Alignment.TopStart
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    model = recipe.image,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "Image"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp)
                )
                {
                    IconButton(
                        onClick = {
                            if (isFavorite.value) {
                                viewModel.removeRecipeFromFavorite(recipe)
                            } else {
                                viewModel.favoriteList.add(recipe)
                                viewModel.addRecipeToFavorite(viewModel.favoriteList)

                            }
                            isFavorite.value = !isFavorite.value
                        },
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(25.dp)
                            )
                    ) {
                        Icon(
                            modifier = Modifier
                                .background(color = Color.White)
                                .size(25.dp),
                            painter = painterResource(R.drawable.ic_favorite),
                            contentDescription = "RecipeTopBar Page",
                            tint = if (isFavorite.value) MainColor else Gray
                        )
                    }
                }

            }

            Text(
                text = recipe.title.toString(),
                fontFamily = semiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 16.dp, top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemScreenPreview() {
    //   RecipeItemScreen(modifier = Modifier)
}