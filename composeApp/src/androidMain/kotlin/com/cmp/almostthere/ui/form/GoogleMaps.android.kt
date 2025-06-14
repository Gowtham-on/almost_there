package com.cmp.almostthere.ui.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmp.almostthere.model.MapDetails
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
actual fun GoogleMaps(mapDetails: MapDetails) {
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(mapDetails.lat, mapDetails.lng) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                LatLng(mapDetails.lat, mapDetails.lng), 18.0f
            )
        )
    }

    val markerState = remember(mapDetails.lat, mapDetails.lng) {
        com.google.maps.android.compose.MarkerState(
            position = LatLng(
                mapDetails.lat,
                mapDetails.lng
            )
        )
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            mapToolbarEnabled = false,
            zoomControlsEnabled = false,
            zoomGesturesEnabled = false,
            tiltGesturesEnabled = false,
            scrollGesturesEnabled = false,
        )

    ) {
        Marker(
            state = markerState
        )
    }
}