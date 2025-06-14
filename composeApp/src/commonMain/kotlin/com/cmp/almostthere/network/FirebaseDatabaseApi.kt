package com.cmp.almostthere.network

interface FirebaseDatabaseApi {
    suspend fun setUserData(userId: String, name: String, deviceId: String)
    suspend fun getUserData(userId: String): UserData?
}

data class UserData(
    val name: String,
    val deviceId: String
)


val firebaseApi = getFirebaseDatabaseApi()

suspend fun saveUser(userId: String, name: String, deviceId: String) {
    firebaseApi.setUserData(userId, name, deviceId)
}

suspend fun loadUser(userId: String): UserData? {
    return firebaseApi.getUserData(userId)
}

expect fun getFirebaseDatabaseApi(): FirebaseDatabaseApi
