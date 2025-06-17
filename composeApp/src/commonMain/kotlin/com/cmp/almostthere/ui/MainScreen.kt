package com.cmp.almostthere.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmp.almostthere.components.BottomNavigationBar
import com.cmp.almostthere.navigation.BottomNavigationItem
import com.cmp.almostthere.navigation.BottomNavigationNavGraph
import com.cmp.almostthere.viewmodel.TriggerViewmodel

@Composable
fun MainScreen(navController: NavHostController, viewmodel: TriggerViewmodel) {
    val navigationItems = remember {
        listOf(
            BottomNavigationItem.Home,
            BottomNavigationItem.Triggers,
            BottomNavigationItem.Settings,
        )
    }

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items = navigationItems, navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding(),
                start = it.calculateStartPadding(LayoutDirection.Ltr),
                end = it.calculateStartPadding(LayoutDirection.Ltr)
            ),
        ) {
            BottomNavigationNavGraph(
                navController = navController,
                viewmodel = viewmodel
            )
        }
    }
}