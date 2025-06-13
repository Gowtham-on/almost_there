package com.cmp.almostthere.model

import kotlinx.serialization.Serializable

@Serializable
data class MapDetails(
    val placeId: String = "",
    val name: String = "",
    val lat: Double = -0.0,
    val lng: Double = -0.0,
    val address: String = "",
)