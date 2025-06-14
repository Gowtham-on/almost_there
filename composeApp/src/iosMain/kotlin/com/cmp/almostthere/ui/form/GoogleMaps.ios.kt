package com.cmp.almostthere.ui.form

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSCameraUpdate
import cocoapods.GoogleMaps.GMSMapView
import com.cmp.almostthere.model.MapDetails
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GoogleMaps(mapDetails: MapDetails) {
    val mapView = remember { GMSMapView() }
    val cameraPosition = GMSCameraPosition.cameraWithLatitude(
        latitude = mapDetails.lat,
        longitude = mapDetails.lng,
        zoom = 14.0f
    )
    val cameraUpdate = GMSCameraUpdate.setCamera(cameraPosition)
    mapView.moveCamera(cameraUpdate)

    UIKitView(
        modifier = Modifier.fillMaxSize(),
        factory = { mapView }
    )
}