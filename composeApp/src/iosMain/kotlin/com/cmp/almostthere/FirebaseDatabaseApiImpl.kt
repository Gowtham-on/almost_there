package com.cmp.almostthere

import cocoapods.FirebaseDatabase.*
import com.cmp.almostthere.network.FirebaseDatabaseApi
import com.cmp.almostthere.network.UserData
import kotlinx.cinterop.*
import platform.Foundation.*

class FirebaseDatabaseApiImpl : FirebaseDatabaseApi {
    private val db = FIRDatabase.database().reference()

    override suspend fun setUserData(userId: String, name: String, deviceId: String) {
        val userMap = mapOf(
            "name" to name,
            "deviceId" to deviceId
        )
        db.child(userId).setValue(userMap as Map<Any?, *>)
    }

    override suspend fun getUserDataFromToken(token: String): UserData? {
        val deferred = kotlinx.coroutines.CompletableDeferred<UserData?>()

        db.child("token").child(token).observeSingleEventOfType(
            FIRDataEventType.FIRDataEventTypeValue,
            withBlock = { snapshot ->
                val value = snapshot.value as? Map<*, *>
                val name = value?.get("name") as? String
                val deviceId = value?.get("deviceId") as? String
                deferred.complete(
                    if (name != null && deviceId != null) UserData(name, deviceId) else null
                )
            }
        )
        return deferred.await()
    }

    override suspend fun getUserDataFromId(userId: String): UserData? {
        val deferred = kotlinx.coroutines.CompletableDeferred<UserData?>()

        db.child("id").child(userId).observeSingleEventOfType(
            FIRDataEventType.FIRDataEventTypeValue,
            withBlock = { snapshot ->
                val value = snapshot.value as? Map<*, *>
                val name = value?.get("name") as? String
                val deviceId = value?.get("deviceId") as? String
                deferred.complete(
                    if (name != null && deviceId != null) UserData(name, deviceId) else null
                )
            }
        )
        return deferred.await()
    }


    override suspend fun mapUserIdWithToken(userId: String, token: String) {
        db.child(userId).setValue(token)
    }

    override suspend fun getDeviceIdFromId(userId: String): String? {
        return ""
    }
}
