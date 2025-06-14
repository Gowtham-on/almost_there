package com.cmp.almostthere

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cmp.almostthere.navigation.NavGraph
import com.cmp.almostthere.theme.AlmostThereTheme
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Preview
fun App() {

    AlmostThereTheme {
        Scaffold {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(
                    top = it.calculateTopPadding(),
                )
            ) {
                val notificationPermission = rememberPermissionState(
                    Permission.Notification
                )
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    if (!notificationPermission.status.isGranted) {
                        notificationPermission.launchPermissionRequest()
                    }
                }
                NavGraph(navController)
            }
        }
    }
}

