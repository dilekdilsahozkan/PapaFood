package com.dilsahozkan.papafood.presentation.navigation

import com.dilsahozkan.papafood.R

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: Int
) {

    data object Home : BottomBar(
        route = "recipe",
        title = "Home",
        icon = R.drawable.ic_home
    )

    data object Favorite : BottomBar(
        route = "favorite",
        title = "Favorite",
        icon = R.drawable.ic_favorite
    )
}
