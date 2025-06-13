package com.cmp.almostthere.ui.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import com.devtamuno.kmp.contactpicker.rememberContactPickerState
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SelectContactComponent() {

    val viewmodel = koinKmpViewModel<TriggerViewmodel>()

    val contactPermission = rememberPermissionState(
        Permission.ReadContacts
    )

    val contactPicker = rememberContactPickerState {
        if (it != null) {
            viewmodel.setContact(it)
        }
    }
    val contactName = viewmodel.selectedContact?.name ?: "Select a contact"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (contactPermission.status.isGranted) {
                    contactPicker.launchContactPicker()
                } else {
                    contactPermission.launchPermissionRequest()
                }
            }
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = contactName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surface,
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Select Contact",
            tint = MaterialTheme.colorScheme.surface
        )
    }
}