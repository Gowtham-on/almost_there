package com.cmp.almostthere.ui.home

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.add_icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.navigation.Routes
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeTab(navController: NavHostController) {
    val viewModel = koinKmpViewModel<TriggerViewmodel>()

    Scaffold(
        floatingActionButton = {
            FloatingButton(navController)
        },
    ) { innerPadding ->
        Column {
            AppHeader(
                title = "ETA Triggers",
                showRightIcon = true,
                onRightIconClick = {
                    navController.navigate(Routes.TriggerForm)
                },
                modifier = Modifier.padding(horizontal = 15.dp),
            )
        }
    }
}

@Composable
fun FloatingButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Routes.TriggerForm)
        },
        shape = RoundedCornerShape(100.dp),
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(Res.drawable.add_icon),
            contentDescription = "Add"
        )
    }
}