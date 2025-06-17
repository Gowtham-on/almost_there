package com.cmp.almostthere.ui.form

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.components.SearchBar
import com.cmp.almostthere.components.SearchWithSuggestions
import com.cmp.almostthere.database.getTriggerDao
import com.cmp.almostthere.model.TriggerType
import com.cmp.almostthere.utils.ShowAlertDialog
import com.cmp.almostthere.utils.getUserId
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import kotlinx.coroutines.launch

@Composable
fun TriggerForm(navigationController: NavHostController, viewmodel: TriggerViewmodel) {
    var messageText = remember { mutableStateOf("") }

    val dao = getTriggerDao()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewmodel.userId = getUserId() ?: ""
    }

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
            leftIcon = Icons.Default.Close,
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
            SearchWithSuggestions(
                placeholder = "Search for a destination",
                contentDesc = "Search",
                showLeadingIcon = true,
                viewmodel = viewmodel
            )
            Box(
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
            ) {
                GoogleMaps(viewmodel.destinationPlace)
            }
            Text(
                "Trigger",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            GetTriggerOptions {
                viewmodel.setUserTriggerType(it)
            }
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                "Contact",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            SelectContactComponent(viewmodel)
            Text(
                "Message",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.surface,
            )
            SearchBar(
                text = messageText.value,
                placeholder = "Type your message here",
                contentDesc = "Message",
                showLeadingIcon = false,
                modifier = Modifier.height(120.dp)
            ) { message ->
                messageText.value = message
            }
            Button(
                onClick = {
                    if (viewmodel.destinationPlace.placeId.isNotEmpty() &&
                        viewmodel.triggerType != TriggerType.NONE &&
                        viewmodel.receiverData.userId.isNotEmpty() &&
                        viewmodel.message.isNotEmpty()
                    ) {
                        viewmodel.setUserMessage(messageText.value)
                        val triggerDetails = viewmodel.getTriggerDetails()
                        scope.launch {
                            dao.insertTriggerDetails(triggerDetails)
                            navigationController.popBackStack()
                        }
                    } else {
                        viewmodel.setDialogTexts(
                            "Enter all fields",
                            "Some of the fields are not filled. Please fill all the fields",
                            confirm = "Ok"
                        )
                        viewmodel.showAlertDialog = true
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                ),
                border = BorderStroke(width = 1.dp, color = Color.Black.copy(alpha = 0.7f)),
                contentPadding = PaddingValues(vertical = 15.dp),
                content = {
                    Text(
                        "Save",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.surface
                    )
                },
            )
            Spacer(modifier = Modifier.height(15.dp))

            if (viewmodel.showAlertDialog)
                ShowAlertDialog(
                    show = true,
                    title = viewmodel.alertDialogTitle,
                    message = viewmodel.alertDialogDescription,
                    confirmButtonText = viewmodel.confirmButtonText,
                    dismissButtonText = viewmodel.dismissButtonText,
                    onDismiss = {
                        viewmodel.showAlertDialog = false
                        viewmodel.clearReceiverData()
                    },
                    onConfirm = {
                        viewmodel.showAlertDialog = false
                    }
                )
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
                        tint = MaterialTheme.colorScheme.surface
                    )
                else
                    Spacer(
                        modifier = Modifier.size(18.dp)
                    )
            }

        }
    }
}