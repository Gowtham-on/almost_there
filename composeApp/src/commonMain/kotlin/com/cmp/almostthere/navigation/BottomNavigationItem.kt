package com.cmp.almostthere.navigation

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.details_selected
import almostthere.composeapp.generated.resources.details_unselected
import almostthere.composeapp.generated.resources.home_selected
import almostthere.composeapp.generated.resources.home_unselected
import almostthere.composeapp.generated.resources.settings_selected
import almostthere.composeapp.generated.resources.settings_unselected
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomNavigationItem(
    val route: Routes,
    val title: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
) {
    data object Home : BottomNavigationItem(
        Routes.Home,
        "Deals",
        selectedIcon = Res.drawable.home_selected,
        unselectedIcon = Res.drawable.home_unselected
    )

    data object Triggers :
        BottomNavigationItem(
            Routes.Triggers,
            title = "Details",
            selectedIcon = Res.drawable.details_selected,
            unselectedIcon = Res.drawable.details_unselected
        )

    data object Settings :
        BottomNavigationItem(
            Routes.Settings,
            title = "Settings",
            selectedIcon = Res.drawable.settings_selected,
            unselectedIcon = Res.drawable.settings_unselected
        )

}