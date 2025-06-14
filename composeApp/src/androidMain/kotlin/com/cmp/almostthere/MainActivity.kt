package com.cmp.almostthere

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.cmp.almostthere.network.FirebaseDatabaseApi
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Firebase.initialize(this) // This line

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                lifecycleScope.launch {
                    val firebaseDbApi = FirebaseDatabaseApiImpl()
                    firebaseDbApi.setUserData(
                        userId = generateUniqueUserId(firebaseDbApi),
                        deviceId = token ?: "",
                        name = ""
                    )
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

suspend fun generateUniqueUserId(firebaseDbApi: FirebaseDatabaseApi): String {
    while (true) {
        val candidate = generateRandomCode()
        val existing = firebaseDbApi.getUserData(candidate)
        if (existing == null) {
            return candidate
        }
    }
}

fun generateRandomCode(length: Int = 4): String {
    val chars = ('A'..'Z')
    return (1..length)
        .map { chars.random() }
        .joinToString("")
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}