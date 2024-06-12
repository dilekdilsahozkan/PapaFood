package com.dilsahozkan.papafood.presentation.navigation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import com.dilsahozkan.papafood.presentation.notification.NotificationScreen
import com.dilsahozkan.papafood.presentation.search.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController, startDestination: String) {
    Scaffold(
        bottomBar = {
            BottomNavigationScreen(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = BottomBar.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(route = BottomBar.Search.route) {
                SearchScreen(navController = navController)
            }
            composable(route = BottomBar.Favorite.route) {
                FavoriteScreen(navController = navController)
            }
            composable(
                route = "notifications?titles={titles}",
                arguments = listOf(navArgument("titles") {
                    type = NavType.StringType; defaultValue = "[]"
                })
            ) { backStackEntry ->
                val titlesJson = backStackEntry.arguments?.getString("titles")
                NotificationScreen(navController = navController, titlesJson = titlesJson)
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


private fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

object Destination {
    const val NOTIFICATIONS = "notifications"
    const val RECIPE_DETAIL = "recipe_detail"
    const val NOTIFICATIONS_TEST = "notifications_test"
}