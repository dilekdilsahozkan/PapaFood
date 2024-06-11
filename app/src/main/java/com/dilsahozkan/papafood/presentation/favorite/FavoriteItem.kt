package com.dilsahozkan.papafood.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import com.dilsahozkan.papafood.presentation.viewmodel.FavoriteViewModel
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.semiBold

@Composable
fun FavoriteItemScreen(
    recipe: FavoriteEntity,
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .height(height = 110.dp)
            .padding(16.dp)
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp),
                model = recipe.image,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Image"
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                text = recipe.title.toString(),
                fontFamily = semiBold,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(0.01f))

            IconButton(
                onClick = {
                    viewModel.deleteRecipe(recipe)
                },
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "Time",
                    tint = MainColor
                )
            }
        }
    }
}

