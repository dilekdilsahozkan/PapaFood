package com.dilsahozkan.papafood.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.dilsahozkan.papafood.ui.theme.Gray
import com.dilsahozkan.papafood.ui.theme.MainColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen(navController: NavHostController) {
    val navScreens = listOf(BottomBar.Home, BottomBar.Favorite)
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar(containerColor = Color.White) {
        navScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = MainColor,
                    unselectedIconColor = Gray,
                    selectedTextColor = MainColor,
                    unselectedTextColor = Gray
                ),
                label = { Text(item.title) },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

    }
}