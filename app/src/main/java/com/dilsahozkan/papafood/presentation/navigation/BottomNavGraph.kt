package com.dilsahozkan.papafood.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dilsahozkan.papafood.presentation.detail.RecipeDetailScreen
import com.dilsahozkan.papafood.presentation.favorite.FavoriteScreen
import com.dilsahozkan.papafood.presentation.homePage.HomeScreen
import com.dilsahozkan.papafood.presentation.splash.SplashScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationScreen(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Destination.SPLASH
        ) {
            composable(route = Destination.SPLASH) {
                SplashScreen(
                    onSplashFinished = {
                        if (it) {
                            navController.navigate(BottomBar.Home.route)
                        }
                    }
                )
            }
            composable(route = BottomBar.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(route = BottomBar.Favorite.route) {
                FavoriteScreen(navController = navController)
            }
            composable(
                route = Destination.RECIPE_DETAIL + "/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                RecipeDetailScreen(navController, backStackEntry.arguments?.getInt("recipeId"))
            }
        }
    }
}

object Destination {
    const val SPLASH = "splash"
    const val RECIPE_DETAIL = "recipe_detail"
}