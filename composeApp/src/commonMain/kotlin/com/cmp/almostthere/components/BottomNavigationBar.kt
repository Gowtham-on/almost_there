package com.cmp.almostthere.components

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.add_icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
                        Icon(
                            painter = painterResource(Res.drawable.add_icon),
                            contentDescription = bottomNavigationItem.title
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