package com.dilsahozkan.papafood.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.dilsahozkan.papafood.presentation.homePage.RecipeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(bottomBar = {
        BottomAppBar(containerColor = Color.White) {
            BottomNavigationScreen(navController = navController)
        }
    }) {
        BottomNavGraph(navController = navController)
    }
}