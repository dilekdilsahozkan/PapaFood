package com.dilsahozkan.papafood.presentation.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
fun RecipeSliderScreen(
    recipe: Recipe,
    navController: NavController
) {

    val formattedScore = String.format("%.1f", recipe.score)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .size(width = 350.dp, height = 295.dp)
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navController.navigate("recipe_detail/${recipe.id}")
            },
    ) {

        Column(Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth(),
                model = recipe.image,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Image"
            )

            Text(
                text = recipe.title.toString(),
                fontFamily = semiBold,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.padding(top = 4.dp),
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
                    color = Color.Black,
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
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

    }
}