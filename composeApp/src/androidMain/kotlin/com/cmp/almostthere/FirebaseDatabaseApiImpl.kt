package com.cmp.almostthere

import com.cmp.almostthere.network.FirebaseDatabaseApi
import com.cmp.almostthere.network.UserData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseApiImpl : FirebaseDatabaseApi {
    private val db = FirebaseDatabase.getInstance().reference

    override suspend fun setUserData(userId: String, name: String, deviceId: String) {
        val userMap = mapOf(
            "name" to name,
            "userId" to userId
        )
        db.child("tokens").child(deviceId).setValue(userMap).await()
    }

    override suspend fun getUserDataFromToken(token: String): UserData? {
        val snapshot = db.child("tokens").child(token).get().await()
        val name = snapshot.child("name").getValue(String::class.java)
        val userId = snapshot.child("userId").getValue(String::class.java)
        return if (name != null && userId != null) UserData(name, userId) else null
    }

    override suspend fun getUserDataFromId(id: String): UserData? {
        val snapshot = db.child("id").child(id).get().await()
        val name = snapshot.child("name").getValue(String::class.java)
        val userId = snapshot.child("userId").getValue(String::class.java)
        val token = snapshot.child("token").getValue(String::class.java)
        return if (name != null && userId != null && token != null) UserData(
            name,
            userId,
            token
        ) else null
    }

    override suspend fun mapUserIdWithToken(
        userId: String,
        token: String
    ) {
        val userMap = mapOf(
            "name" to "",
            "userId" to userId,
            "token" to token
        )
        db.child("id").child(userId).setValue(userMap).await()
    }

    override suspend fun getDeviceIdFromId(userId: String): String? {
        val snapshot = db.child("id").child(userId).get().await().getValue(String::class.java)
        return snapshot
    }
}
