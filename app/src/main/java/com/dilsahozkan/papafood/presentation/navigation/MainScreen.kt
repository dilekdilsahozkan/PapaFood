package com.dilsahozkan.papafood.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val shouldShowBottomBar = currentDestination?.route != Destination.RECIPE_DETAIL + "/{recipeId}"

    Scaffold(bottomBar = {
        if (shouldShowBottomBar) {
            BottomAppBar(containerColor = Color.White) {
                BottomNavigationScreen(navController = navController)
            }
        }
    }) {
        BottomNavGraph(navController = navController)
    }
}