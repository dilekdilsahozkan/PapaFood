package com.dilsahozkan.papafood.presentation.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.ui.theme.mediumFont
import com.dilsahozkan.papafood.ui.theme.semiBold

@Composable
fun FavoriteScreen() {

    val image = painterResource(id = R.drawable.img_banner3)

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

        Row(
            modifier = Modifier
                .padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(100.dp),
                painter = image,
                contentScale = ContentScale.FillBounds,
                contentDescription = "Image"
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Thai Basil Pork",
                fontFamily = semiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen()
}