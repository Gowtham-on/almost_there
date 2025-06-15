package com.cmp.almostthere

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.cmp.almostthere.database.setDBContext
import com.cmp.almostthere.network.FirebaseDatabaseApi
import com.cmp.almostthere.utils.setContext
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        setContext(this)
        setDBContext(this)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                if (token.isNullOrEmpty()) {
                    Log.e("FCM", "Token is null or empty")
                    return@addOnCompleteListener
                }

                lifecycleScope.launch {
                    val firebaseDbApi = FirebaseDatabaseApiImpl()
                    val userData = firebaseDbApi.getUserDataFromToken(token)
                    if (userData == null) {
                        val uniqueId = generateUniqueUserId(firebaseDbApi, token)
                        saveUserSession(
                            context = this@MainActivity,
                            userId = uniqueId,
                            deviceId = token
                        )
                        firebaseDbApi.setUserData(
                            userId = uniqueId,
                            deviceId = token,
                            name = ""
                        )
                    } else {
                        saveUserSession(
                            context = this@MainActivity,
                            userId = userData.userId,
                            deviceId = token
                        )
                    }
                }

            } else {
                Log.e("FCM", "Fetching FCM registration token failed", task.exception)
            }
        }
        setContent {
            App()
        }
    }
}

suspend fun generateUniqueUserId(firebaseDbApi: FirebaseDatabaseApi, token: String): String {
    while (true) {
        val randomId = generateRandomCode()
        val existing = firebaseDbApi.getDeviceIdFromId(randomId)
        if (existing == null) {
            firebaseDbApi.mapUserIdWithToken(randomId, token)
            return randomId
        }
    }
}

fun generateRandomCode(length: Int = 4): String {
    val chars = ('0'..'9')
    return (1..length)
        .map { chars.random() }
        .joinToString("")
}

fun saveUserSession(context: Context, userId: String, deviceId: String) {
    val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    prefs.edit {
        putString("userId", userId)
            .putString("deviceId", deviceId)
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}