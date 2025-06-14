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
            "deviceId" to deviceId
        )
        db.child(userId).setValue(userMap).await()
    }

    override suspend fun getUserData(userId: String): UserData? {
        val snapshot = db.child(userId).get().await()
        val name = snapshot.child("name").getValue(String::class.java)
        val deviceId = snapshot.child("deviceId").getValue(String::class.java)
        return if (name != null && deviceId != null) UserData(name, deviceId) else null
    }
}
