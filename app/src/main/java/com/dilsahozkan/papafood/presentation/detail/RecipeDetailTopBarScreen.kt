package com.dilsahozkan.papafood.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dilsahozkan.papafood.data.remote.model.RecipeDetail
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.SoftOrangeColor
import kotlin.math.max
import kotlin.math.min

@Composable
fun RecipeDetailTopBarScreen(recipe: RecipeDetail, scrollState: LazyListState, navController: NavController) {
    val imageHeight = 294.dp
    val maxOffset = with(LocalDensity.current) { imageHeight.roundToPx() }
    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)
    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    Box(
    modifier = Modifier
    .height(350.dp)
    .offset { IntOffset(x = 0, y = -offset) }
    .background(White)

    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(imageHeight)
                    .graphicsLayer { alpha = 1f - offsetProgress }
            ) {
//                AsyncImage(
//                    model = recipe.imageUrl,
//                    contentDescription = null,
//                    contentScale = ContentScale.FillBounds,
//                    modifier = Modifier.fillMaxWidth()
//                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
//                Text(
//                    text = recipe.name.toString().lowercase()
//                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
//                    fontSize = 20.sp,
//                    fontFamily = semibold,
//                    modifier = Modifier
//                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
//                        .scale(1f - 0.25f * offsetProgress)
//                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(56.dp)
                .padding(horizontal = 16.dp)
        ) {
            IconButton(
                onClick = {
                    navController.navigate("recipe")
                },
                modifier = Modifier
                    .padding(top = 10.dp, end = 10.dp)
                    .size(48.dp)
                    .background(
                        MainColor,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "RecipeTopBar Page",
                    tint = SoftOrangeColor
                )
            }
        }
    }
}