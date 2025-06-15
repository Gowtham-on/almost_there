package com.cmp.almostthere.network

interface FirebaseDatabaseApi {
    suspend fun setUserData(userId: String, name: String, deviceId: String)
    suspend fun getUserDataFromToken(token: String): UserData?
    suspend fun getUserDataFromId(userId: String): UserData?
    suspend fun mapUserIdWithToken(userId: String, token: String)
    suspend fun getDeviceIdFromId(userId: String): String?
}

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

    suspend fun loadUserFromToken(token: String): UserData? {
        return firebaseApi.getUserDataFromToken(token)
    }
    suspend fun loadUserFromId(userId: String): UserData? {
        return firebaseApi.getUserDataFromId(userId)
    }

}

expect fun getFirebaseDatabaseApi(): FirebaseDatabaseApi
