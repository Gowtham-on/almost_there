package com.cmp.almostthere.database

import android.content.Context
import androidx.compose.runtime.Composable

lateinit var appContext: Context

fun setDBContext(context: Context) {
    appContext = context
}

@Composable
actual fun getTriggerDao(): TriggerDetailsDao {
    val dao = getTriggerDetailsDatabase(appContext).triggerDetailsDao()
    return dao
}