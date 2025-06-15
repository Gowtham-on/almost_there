package com.cmp.almostthere.database

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun getTriggerDao(): TriggerDetailsDao {
    val dao = remember {
        getTriggerDetailsDatabase().triggerDetailsDao()
    }
    return dao
}