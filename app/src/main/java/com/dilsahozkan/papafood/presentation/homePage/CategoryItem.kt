package com.dilsahozkan.papafood.presentation.homePage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.SoftOrangeColor

@Composable
fun CategoryItem(
    modifier: Modifier,
    category: String,
    onItemClick: (String) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = SoftOrangeColor
        ),
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                spotColor = Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onItemClick(category)
            }
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = category)
        }
    }
}