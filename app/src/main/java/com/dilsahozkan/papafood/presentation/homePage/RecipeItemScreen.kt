package com.dilsahozkan.papafood.presentation.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.data.remote.model.Recipe
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.regular
import com.dilsahozkan.papafood.ui.theme.semiBold

@Composable
fun RecipeItemScreen(modifier: Modifier) {

    val image = painterResource(id = R.drawable.img_banner1)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .height(320.dp)
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
    ) {

        Column(Modifier.fillMaxWidth()) {

            Box(
                contentAlignment = Alignment.TopEnd){
                Image(
                    modifier = Modifier
                        .height(210.dp)
                        .fillMaxWidth(),
                    painter = image,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Image"
                )
                Icon(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource(id = R.drawable.ic_favorite),
                    tint = Gray,
                    contentDescription = "Time"
                )
            }

            Text(
                text = "Spiced Fried Chicken",
                fontFamily = semiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp)
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
                    text = "4.5",
                    fontFamily = regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Icon(
                    modifier = Modifier.padding(start = 25.dp),
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "Time"
                )
                Text(
                    text = "13 minutes",
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