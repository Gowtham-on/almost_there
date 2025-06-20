package com.cmp.almostthere.ui.home

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.add_icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.components.ToggleSwitch
import com.cmp.almostthere.database.TriggerDetailsDao
import com.cmp.almostthere.database.getTriggerDao
import com.cmp.almostthere.model.TriggerDetails
import com.cmp.almostthere.navigation.Routes
import com.cmp.almostthere.network.FirebaseApiImpl
import com.cmp.almostthere.utils.getUserId
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeTab(navController: NavHostController, viewmodel: TriggerViewmodel) {
    var id by remember { mutableStateOf("") }
    val dao = getTriggerDao()
    val triggerDetails by dao.getTriggerDetails().collectAsState(emptyList())

    LaunchedEffect(Unit) {
        id = getUserId().toString()
        val data = FirebaseApiImpl.loadUserFromId(id)
        if (data != null) {
            viewmodel.setCurrentUserInfoData(data)
        }
    }

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

            Spacer(
                Modifier.height(10.dp)
            )

            LazyColumn {
                items(triggerDetails.size) {
                    TriggerCard(triggerDetails[it], dao)
                    Spacer(
                        Modifier.height(15.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TriggerCard(details: TriggerDetails, dao: TriggerDetailsDao) {
    val scope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 14.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(15.dp),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(3f)
        ) {
            Text(
                details.location.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                Text(
                    "Contact: ${
                        if (details.receiverDetails.name.isEmpty())
                            details.receiverDetails.name
                        else
                            details.receiverDetails.userId
                    }",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }

        ToggleSwitch(
            checked = details.isEnabled,
            onCheckedChange = {
                val newDetails = details.copy(isEnabled = it)
                scope.launch {
                    dao.insertTriggerDetails(newDetails)
                }
            },
        )
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