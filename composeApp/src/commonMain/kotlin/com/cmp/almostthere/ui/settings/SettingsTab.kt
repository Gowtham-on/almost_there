package com.cmp.almostthere.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cmp.almostthere.components.AppHeader
import com.cmp.almostthere.components.ToggleSwitch
import com.cmp.almostthere.navigation.Routes
import com.cmp.almostthere.network.UserData
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SettingsTab(navController: NavHostController, viewmodel: TriggerViewmodel) {

    var showNameChangeDialog by remember { mutableStateOf(false) }
    var userData by remember { mutableStateOf(UserData()) }

    LaunchedEffect(viewmodel.currentUserData) {
        userData = viewmodel.currentUserData
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(horizontal = 15.dp),
    ) {
        AppHeader(
            title = "Settings",
            modifier = Modifier.padding(horizontal = 15.dp),
        )
        Spacer(
            Modifier
        )
        GetUserProfileData(userData) {
            showNameChangeDialog = true
        }
        Spacer(
            Modifier
        )
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        Spacer(
            Modifier.height(5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        navController.navigate(Routes.ThemeChangeView)
                    },
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GetSettingsTextPair(
                "Theme",
                "Dark",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Enter",
                tint = MaterialTheme.colorScheme.surface,
            )
        }
        Spacer(
            Modifier.height(10.dp)
        )
        GetPermissionsOptions()
        Spacer(
            Modifier.height(10.dp)
        )
        GetBatteryOption()
        if (showNameChangeDialog)
            ShowUserNameChangeDialog(userData, viewmodel) {
                showNameChangeDialog = false
            }
    }
}

@Composable
fun GetUserProfileData(userData: UserData, onEditClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "avatar",
                modifier = Modifier.padding(12.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    if (userData.name.isEmpty()) "User_${userData.userId}" else userData.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.surface,
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(20.dp).clickable(
                        onClick = {
                            onEditClick()
                        }
                    )
                )
            }
            Text(
                "Your ID: ${userData.userId}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.surface,
            )
        }

    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetPermissionsOptions() {
    val backgroundLocationPermission = rememberPermissionState(
        Permission.BackgroundLocation
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "Permissions",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.surface,
        )
        GetSettingsText(
            "Background Location",
            "Allow the app to access your location in the background",
            isEnabled = backgroundLocationPermission.status.isGranted,
            onCheckedChange = {
                backgroundLocationPermission.openAppSettings()
            }
        )
        GetSettingsText(
            "Auto-start on boot",
            "Allow the app to start automatically when your device boots up"
        )
    }
}

@Composable
fun GetBatteryOption() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "Battery",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.surface,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GetSettingsText(
                "Battery Optimization",
                "Instructions on how to exclude the app from battery optimization"
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Enter",
                tint = MaterialTheme.colorScheme.surface,
            )
        }

    }
}

@Composable
fun GetSettingsText(
    title: String,
    desc: String,
    isEnabled: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row {
        GetSettingsTextPair(title, desc, modifier = Modifier.weight(1f))
        ToggleSwitch(checked = isEnabled, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun GetSettingsTextPair(title: String, desc: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.surface,
        )
        Text(
            desc,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.surface,
        )
    }
}

@Composable
fun ShowUserNameChangeDialog(
    userData: UserData,
    viewmodel: TriggerViewmodel,
    onDismiss: () -> Unit = {}
) {

    var newName by remember { mutableStateOf("") }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.secondary,
        onDismissRequest = {

        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Change your name")
                OutlinedTextField(
                    value = newName,
                    onValueChange = {
                        newName = it
                    },
                    placeholder = {
                        Text(
                            if (userData.name.isNotEmpty()) userData.name else "User_${userData.userId}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        disabledTextColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.tertiary,
                        focusedTextColor = MaterialTheme.colorScheme.surface,
                        unfocusedTextColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    ),
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // update in firebase
                    viewmodel.updateName(newName)
                    // update in viewmodel
                    viewmodel.setCurrentUserInfoData(
                        userData.copy(name = newName)
                    )
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    "Save",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    "Cancel",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }
        }
    )
}