package com.cmp.almostthere.navigation

sealed class BottomNavigationItem(
    val route: Routes,
    val title: String,
    val icon: Int,
) {
    data object Home : BottomNavigationItem(Routes.Home, "Deals", 2)
    data object Triggers :
        BottomNavigationItem(Routes.Triggers, title = "Search", icon = 2)

    data object Settings :
        BottomNavigationItem(Routes.Settings, title = "Free", icon = 2)

}