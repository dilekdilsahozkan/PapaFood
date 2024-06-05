package com.dilsahozkan.papafood.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dilsahozkan.papafood.presentation.favorite.FavoriteScreen
import com.dilsahozkan.papafood.presentation.homePage.HomeScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = BottomBar.Home.route
    ) {
        composable(route = BottomBar.Home.route) {
            HomeScreen(modifier = Modifier)
        }
        composable(route = BottomBar.Favorite.route) {
            FavoriteScreen()
        }
    }
}