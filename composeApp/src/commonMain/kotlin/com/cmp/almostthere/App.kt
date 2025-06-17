package com.cmp.almostthere

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.cmp.almostthere.navigation.NavGraph
import com.cmp.almostthere.theme.AlmostThereTheme
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.PermissionState
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import com.mohamedrejeb.calf.permissions.shouldShowRationale
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Preview
fun App() {

    AlmostThereTheme {
        Scaffold {
            val notificationPermission = rememberPermissionState(Permission.Notification)
            val coarseLocation = rememberPermissionState(Permission.CoarseLocation)
            val fineLocation = rememberPermissionState(Permission.FineLocation)
            val bgLocation = rememberPermissionState(Permission.BackgroundLocation)

            var step by remember { mutableStateOf(0) }

            var viewmodel = koinKmpViewModel<TriggerViewmodel>()

            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(
                    top = it.calculateTopPadding(),
                )
            ) {
                when (step) {
                    0 -> {
                        PermissionRequestView(
                            permissionState = notificationPermission,
                            message = "We use notifications to alert you of important updates.",
                            onGranted = { step = 1 }
                        )
                    }

                    1 -> {
                        PermissionRequestView(
                            permissionState = coarseLocation,
                            message = "We need location access for map features.",
                            onGranted = { step = 2 }
                        )
                    }

                    2 -> {
                        PermissionRequestView(
                            permissionState = fineLocation,
                            message = "Precise location lets us serve you better.",
                            onGranted = { step = 3 }
                        )
                    }

                    3 -> {
                        PermissionRequestView(
                            permissionState = bgLocation,
                            message = "Background location keeps features running even when the app is closed.",
                            onGranted = { step = 4 }
                        )
                    }
                }
                val navController = rememberNavController()
                NavGraph(navController, viewmodel)
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestView(
    permissionState: PermissionState,
    message: String,
    onGranted: () -> Unit
) {
    when {
        permissionState.status.isGranted -> {
            LaunchedEffect(Unit) { onGranted() }
        }

        permissionState.status.shouldShowRationale -> {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(message, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { permissionState.launchPermissionRequest() }) {
                    Text("Allow")
                }
            }
        }

        else -> {
            // Initial request, or denied with "Don't ask again"
            LaunchedEffect(Unit) {
                permissionState.launchPermissionRequest()
            }
        }
    }
}
