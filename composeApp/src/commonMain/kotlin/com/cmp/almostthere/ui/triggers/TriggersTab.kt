package com.cmp.almostthere.ui.triggers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.touchlab.kermit.Logger
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.ui.form.GoogleMaps
import com.cmp.almostthere.ui.settings.GetSettingsTextPair
import com.cmp.almostthere.viewmodel.TriggerViewmodel

@Composable
fun TriggersTab(navController: NavHostController, viewmodel: TriggerViewmodel) {

    val triggerDetail = remember { viewmodel.selectedTriggerDetail }
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(horizontal = 15.dp),
    ) {
        AppHeader(
            title = "Trigger Detail",
            modifier = Modifier.padding(horizontal = 15.dp),
        )


        if (triggerDetail != null)
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Spacer(
                    Modifier.height(12.dp)
                )
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                ) {
                    GoogleMaps(triggerDetail.location)
                }
                Text(
                    "Trigger Details",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.surface,
                )

                Spacer(Modifier.height(2.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    GetTriggerDetailsItems(viewmodel, "Destination", triggerDetail.location.name)
                    GetTriggerDetailsItems(viewmodel, "Distance", triggerDetail.location.name)
                    GetTriggerDetailsItems(viewmodel, "ETA", triggerDetail.location.name)
                    GetTriggerDetailsItems(viewmodel, "Message", triggerDetail.message)
                }


            }

    }
}

@Composable
fun GetTriggerDetailsItems(viewmodel: TriggerViewmodel, heading: String, desc: String) {
    Logger.d { "triggerDetails: heading ${viewmodel.selectedTriggerDetail}" }
    Row(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
        )

        GetSettingsTextPair(heading, desc)
    }
}