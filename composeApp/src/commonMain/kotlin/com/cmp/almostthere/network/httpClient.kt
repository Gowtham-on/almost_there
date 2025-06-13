package com.cmp.almostthere.network

import com.cmp.almostthere.model.MapDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

@Serializable
data class GooglePlacesResponse(
    @SerialName("results") val results: List<GooglePlace>
)

@Serializable
data class GooglePlace(
    @SerialName("name") val name: String,
    @SerialName("geometry") val geometry: Geometry,
    @SerialName("place_id") val placeId: String,
    @SerialName("formatted_address") val address: String
)

@Serializable
data class Geometry(
    @SerialName("location") val location: LatLng
)

@Serializable
data class LatLng(
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double
)

suspend fun searchPlaces(query: String, apiKey: String): List<MapDetails> {
    val response: GooglePlacesResponse =
        httpClient.get("https://maps.googleapis.com/maps/api/place/textsearch/json") {
            parameter("query", query)
            parameter("key", apiKey)
        }.body()

    return response.results.map { result ->
        MapDetails(
            placeId = result.placeId,
            name = result.name,
            lat = result.geometry.location.lat,
            lng = result.geometry.location.lng,
            address = result.address
        )
    }
}