package com.dilsahozkan.papafood.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.Green
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.regular
import com.dilsahozkan.papafood.ui.theme.semiBold

@Composable
fun FavoriteItemScreen(recipe: FavoriteEntity) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .size(height = 230.dp, width = 230.dp)
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxWidth(),
                model = recipe.image,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Image"
            )

            Text(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                text = recipe.title.toString(),
                fontFamily = semiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_price),
                    contentDescription = "Time",
                    tint = Green
                )
                Text(
                    text = recipe.readyInMinutes.toString(),
                    fontFamily = regular,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

    }
}

