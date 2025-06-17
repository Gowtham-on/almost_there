package com.cmp.almostthere.ui.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cmp.almostthere.components.SearchBar
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SelectContactComponent(viewmodel: TriggerViewmodel) {

    var contactId = remember { mutableStateOf("") }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBar(
                text = contactId.value,
                placeholder = "Enter the receiver's id",
                contentDesc = "Enter",
                showLeadingIcon = false,
                modifier = Modifier.weight(2f),
                keyBoardType = KeyboardType.Number
            ) {
                contactId.value = it
            }
            Spacer(
                Modifier.width(15.dp)
            )
            Button(
                onClick = {
                    viewmodel.searchUser(contactId.value)
                },
                content = {
                    Text(
                        "Search",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.surface
                    )
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                ),
                contentPadding = PaddingValues(vertical = 15.dp),
            )
        }
        if (viewmodel.showIncorrectId)
            Text(
                "Please enter the correct id",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.surface
            )
        else if (!viewmodel.showIncorrectId && viewmodel.receiverData.name.isNotEmpty())
            Text(
                "${viewmodel.receiverData.name} will be notified",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.surface
            )
    }
}