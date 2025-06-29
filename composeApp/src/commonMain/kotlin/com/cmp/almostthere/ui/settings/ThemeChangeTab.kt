package com.cmp.almostthere.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.components.RadioGroup
import com.cmp.almostthere.components.RadioItem
import com.cmp.almostthere.utils.Theme
import com.cmp.almostthere.viewmodel.TriggerViewmodel

@Composable
fun ThemeChangeTab(navController: NavHostController, viewmodel: TriggerViewmodel) {

    val radioItems = listOf(
        RadioItem(
            isSelected = viewmodel.currentTheme == Theme.LIGHT,
            name = "Light",
            onClick = {
                viewmodel.setTheme(Theme.LIGHT)
            }
        ),
        RadioItem(
            isSelected = viewmodel.currentTheme == Theme.DARK,
            name = "Dark",
            onClick = {
                viewmodel.setTheme(Theme.DARK)
            }
        ),
        RadioItem(
            isSelected = viewmodel.currentTheme == Theme.SYSTEM,
            name = "System",
            onClick = {
                viewmodel.setTheme(Theme.SYSTEM)
            }
        )
    )

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

        RadioGroup(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            radioItems = radioItems
        )
    }
}