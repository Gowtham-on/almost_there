package com.cmp.almostthere.ui.form

import almostthere.composeapp.generated.resources.Res
import almostthere.composeapp.generated.resources.cross
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.components.SearchBar
import com.cmp.almostthere.model.NotifyType
import com.cmp.almostthere.model.TriggerType

@Composable
fun TriggerForm(navigationController: NavHostController) {

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 14.dp)
    ) {
        AppHeader(
            "New Trigger",
            showLeftIcon = true,
            onLeftIconClick = {
                navigationController.popBackStack()
            },
            leftIcon = Res.drawable.cross,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                "Destination",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            SearchBar(
                placeholder = "Search for a destination",
                contentDesc = "Search",
                showLeadingIcon = true
            )
            Text(
                "Trigger",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            GetTriggerOptions {}
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                "Contact",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            SelectContactComponent()
            Text(
                "Message",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            SearchBar(
                placeholder = "Type your message here",
                contentDesc = "Message",
                showLeadingIcon = false,
                modifier = Modifier.height(120.dp)
            )
            Text(
                "Delivery Method",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            GetDeliveryType()
        }
    }

}

@Composable
fun GetTriggerOptions(triggerTypeSelection: (TriggerType) -> Unit = {}) {

    var selectedType = remember { mutableStateOf(TriggerType.NONE) }

    listOf<Pair<TriggerType, String>>(
        Pair(
            TriggerType.NEAR_DESTINATION,
            "When I'm near destination"
        ),
        Pair(
            TriggerType.CUSTOM_TIME_AWAY,
            "When my ETA is less than"
        ),
    ).map {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(
                        width = 1.5.dp,
                        color = if (selectedType.value == it.first) MaterialTheme.colorScheme.inverseSurface
                        else MaterialTheme.colorScheme.inverseSurface.copy(
                            alpha = 0.3f
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    onClick = {
                        selectedType.value = it.first
                        triggerTypeSelection(it.first)
                    }
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    it.second,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedType.value == it.first)
                        MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                )

                if (selectedType.value == it.first)
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Selected Option",
                        modifier = Modifier.size(18.dp),
                    )
                else
                    Spacer(
                        modifier = Modifier.size(18.dp)
                    )
            }

        }
    }
}

@Composable
fun GetDeliveryType() {

    var selectedType = remember { mutableStateOf(NotifyType.NONE) }

    listOf<Pair<NotifyType, String>>(
        Pair(
            NotifyType.WHATSAPP,
            "Whatsapp"
        ),
        Pair(
            NotifyType.SMS,
            "SMS"
        ),
    ).map {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(
                        width = 1.5.dp,
                        color = if (selectedType.value == it.first) MaterialTheme.colorScheme.inverseSurface
                        else MaterialTheme.colorScheme.inverseSurface.copy(
                            alpha = 0.3f
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    onClick = {
                        selectedType.value = it.first
                    }
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    it.second,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedType.value == it.first)
                        MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                )

                if (selectedType.value == it.first)
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Selected Option",
                        modifier = Modifier.size(18.dp),
                    )
                else
                    Spacer(
                        modifier = Modifier.size(18.dp)
                    )
            }

        }
    }
}