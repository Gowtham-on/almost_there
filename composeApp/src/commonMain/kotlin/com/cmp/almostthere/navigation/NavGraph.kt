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
import com.cmp.almostthere.ui.settings.SettingsTab
import com.cmp.almostthere.ui.settings.ThemeChangeTab
import com.cmp.almostthere.viewmodel.TriggerViewmodel

@Composable
fun NavGraph(navController: NavHostController, viewmodel: TriggerViewmodel) {
    NavHost(navController = navController, startDestination = Routes.Main) {
        destinations(navController, viewmodel)
    }
}

@Composable
fun BottomNavigationNavGraph(
    navController: NavHostController,
    viewmodel: TriggerViewmodel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
    ) {
        destinations(navController, viewmodel)
    }
}


fun NavGraphBuilder.destinations(navController: NavHostController, viewmodel: TriggerViewmodel) {
    composable<Routes.Splash> {
        Column {
            Text("splash")
        }
    }
    composable<Routes.Main> {
        MainScreen(navController, viewmodel)
    }
    composable<Routes.Home> {
        HomeTab(navController, viewmodel)
    }
    composable<Routes.Triggers> {
        Text("Triggers")
    }
    composable<Routes.Settings> {
        SettingsTab(navController, viewmodel)
    }
    composable<Routes.TriggerForm> {
        TriggerForm(navController, viewmodel)
    }
    composable<Routes.ThemeChangeView> {
        ThemeChangeTab(navController)
    }
}

