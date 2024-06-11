package com.dilsahozkan.papafood.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilsahozkan.papafood.R
import com.dilsahozkan.papafood.presentation.viewmodel.RecipeViewModel
import com.dilsahozkan.papafood.ui.theme.mediumFont
import com.dilsahozkan.papafood.ui.theme.semiBold
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun NotificationScreen(
    navController: NavController,
    titlesJson: String?
) {

    val titles: List<String> = if (!titlesJson.isNullOrEmpty()) {
        Gson().fromJson(titlesJson, object : TypeToken<List<String>>() {}.type)
    } else {
        emptyList()
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Notification"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = "Notifications",
                    fontSize = 20.sp,
                    fontFamily = mediumFont
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            if (titles.isNotEmpty()) {
                items(titles) { title ->
                    NotificationItem(title = title)
                }
            } else {
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No notifications found",
                            fontSize = 25.sp,
                            fontFamily = semiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}