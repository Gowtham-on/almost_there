package com.cmp.almostthere.firebase

import android.util.Log
import co.touchlab.kermit.Logger
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseCloudMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Logger.d { "FCM token: $token" }
        Log.d("FCM", "Device token: $token")
        // Send this token to your backend or save in Firebase if needed
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "Message received: ${remoteMessage.data}")
        // Handle notification payload here
        // If message contains a notification payload, show a notification, etc.
    }
}