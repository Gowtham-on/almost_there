package com.cmp.almostthere.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmp.almostthere.ui.MainScreen
import com.cmp.almostthere.ui.form.TriggerForm
import com.cmp.almostthere.ui.home.HomeTab

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Main) {
        destinations(navController)
    }
}

@Composable
fun BottomNavigationNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
    ) {
        destinations(navController)
    }
}


fun NavGraphBuilder.destinations(navController: NavHostController) {
    composable<Routes.Splash> {
        Column {
            Text("splash")
        }
    }
    composable<Routes.Main> {
        MainScreen(navController)
    }
    composable<Routes.Home> {
        HomeTab(navController)
    }
    composable<Routes.Triggers> {
        Text("Triggers")
    }
    composable<Routes.Settings> {
        Text("Settings")
    }
    composable<Routes.TriggerForm> {
        TriggerForm(navController)
    }
}

