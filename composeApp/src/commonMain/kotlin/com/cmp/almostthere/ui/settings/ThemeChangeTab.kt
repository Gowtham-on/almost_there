package com.cmp.almostthere.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader

@Composable
fun ThemeChangeTab(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        AppHeader(
            title = "Theme",
            modifier = Modifier.padding(horizontal = 15.dp),
            showLeftIcon = true,
            leftIcon = Icons.Default.ChevronLeft,
            onLeftIconClick = {
                navController.popBackStack()
            }
        )

    }
}