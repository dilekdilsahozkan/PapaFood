package com.dilsahozkan.papafood.presentation.navigation

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

        composable(
            route = Destination.RECIPE_DETAIL + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            RecipeDetailScreen(navController, backStackEntry.arguments?.getInt("recipeId"))
        }
    }
}

object Destination {
    const val RECIPE_DETAIL = "recipe_detail"
}