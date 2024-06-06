package com.dilsahozkan.papafood.presentation.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.regular
import com.dilsahozkan.papafood.ui.theme.semiBold

@SuppressLint("DefaultLocale")
@Composable
fun RecipeItemScreen(
    recipe: Recipe,
    modifier: Modifier,
    navController: NavController
) {

    val formattedScore = String.format("%.1f", recipe.spoonacularScore)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .height(300.dp)
            .padding(top = 16.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
    ) {

        Column {

            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    model = recipe.image,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Image"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(16.dp)
                ) {
                    IconButton(
                        onClick = {
                            //    navController.navigate("favorite")
                        },
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_favorite),
                            contentDescription = "RecipeTopBar Page",
                            tint = Gray,
                        )
                    }
                }
            }

            Text(
                text = recipe.title.toString(),
                fontFamily = semiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    painter = painterResource(id = R.drawable.ic_star),
                    tint = MainColor,
                    contentDescription = "Star"
                )
                Text(
                    text = formattedScore,
                    fontFamily = regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Icon(
                    modifier = Modifier.padding(start = 25.dp),
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "Time"
                )
                Text(
                    text = recipe.readyInMinutes.toString() + " minutes",
                    fontFamily = regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemScreenPreview() {
    //   RecipeItemScreen(modifier = Modifier)
}