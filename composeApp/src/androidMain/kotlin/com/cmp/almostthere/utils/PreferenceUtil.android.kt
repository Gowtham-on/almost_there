package com.cmp.almostthere.utils

import android.content.Context


lateinit var appContext: Context
fun setContext(context: Context) {
    appContext = context
}

actual fun getUserId(): String? = getUserSession(appContext).first
actual fun getDeviceId(): String? = getUserSession(appContext).second

fun getUserSession(context: Context): Pair<String?, String?> {
    val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    val userId = prefs.getString("userId", null)
    val deviceId = prefs.getString("deviceId", null)
    return Pair(userId, deviceId)
}