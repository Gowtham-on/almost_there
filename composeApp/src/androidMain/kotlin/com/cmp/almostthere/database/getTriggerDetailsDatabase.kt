package com.cmp.almostthere.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver


fun getTriggerDetailsDatabase(context: Context): TriggerDatabase {
    val dbFile = context.getDatabasePath("trigger_details.db")

    return Room
        .databaseBuilder<TriggerDatabase>(
            context = context,
            name = dbFile.name
        )
        .setDriver(BundledSQLiteDriver())
        .build()
}