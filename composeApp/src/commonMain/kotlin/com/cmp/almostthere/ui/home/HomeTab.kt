package com.cmp.almostthere.ui.home

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.add_icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.navigation.Routes
import com.cmp.almostthere.utils.getUserId
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeTab(navController: NavHostController) {
    var id by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        id = getUserId().toString()
    }

    Scaffold(
        floatingActionButton = {
            FloatingButton(navController)
        },
    ) { innerPadding ->
        Column {
            Row {
                Text(id.toString())
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