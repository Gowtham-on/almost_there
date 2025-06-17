package com.cmp.almostthere.network

import kotlinx.serialization.Serializable

interface FirebaseDatabaseApi {
    suspend fun setUserData(userId: String, name: String, deviceId: String)
    suspend fun getUserDataFromToken(token: String): UserData?
    suspend fun getUserDataFromId(userId: String): UserData?
    suspend fun mapUserIdWithToken(userId: String, token: String, name: String)
    suspend fun getDeviceIdFromId(userId: String): String?
}

@Serializable
data class UserData(
    val name: String = "",
    val userId: String = "",
    val token: String = ""
)


object FirebaseApiImpl {
    val firebaseApi = getFirebaseDatabaseApi()

    suspend fun saveUser(userId: String, name: String, deviceId: String) {
        firebaseApi.setUserData(userId, name, deviceId)
    }

    suspend fun updateUser(userId: String, token: String, name: String) {
        firebaseApi.setUserData(userId, name = name, token)
        firebaseApi.mapUserIdWithToken(userId, token, name)
    }

    suspend fun loadUserFromToken(token: String): UserData? {
        return firebaseApi.getUserDataFromToken(token)
    }
    suspend fun loadUserFromId(userId: String): UserData? {
        return firebaseApi.getUserDataFromId(userId)
    }

}

expect fun getFirebaseDatabaseApi(): FirebaseDatabaseApi
