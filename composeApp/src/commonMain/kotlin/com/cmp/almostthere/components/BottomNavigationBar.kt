package com.cmp.almostthere.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmp.almostthere.navigation.BottomNavigationItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val destination = items.any { currentDestination?.hasRoute(route = it.route::class) == true }

    AnimatedVisibility(destination) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.secondary,
            tonalElevation = 10.dp
        ) {
            items.forEachIndexed { _, bottomNavigationItem ->
                NavigationBarItem(
                    icon = {
                        if (currentDestination?.hierarchy?.any { it.hasRoute(route = bottomNavigationItem.route::class) } == true)
                            Icon(
                                painter = painterResource(bottomNavigationItem.selectedIcon),
                                contentDescription = bottomNavigationItem.title,
                                modifier = Modifier.size(25.dp)
                            ) else
                            Icon(
                                painter = painterResource(bottomNavigationItem.unselectedIcon),
                                contentDescription = bottomNavigationItem.title,
                                modifier = Modifier.size(25.dp)
                            )
                    },
                    label = { Text(bottomNavigationItem.title) },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(route = bottomNavigationItem.route::class) } == true,
                    onClick = {
                        navController.navigate(bottomNavigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}