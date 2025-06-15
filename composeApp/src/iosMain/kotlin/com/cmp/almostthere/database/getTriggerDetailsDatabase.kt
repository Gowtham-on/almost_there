package com.cmp.almostthere.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getTriggerDetailsDatabase(): TriggerDatabase {
    val dbFile = NSHomeDirectory() + "/trigger_details.db"

    return Room
        .databaseBuilder<TriggerDatabase>(
            name = dbFile,
            factory = { TriggerDatabase::class.instantiateImpl() }
        )
        .setDriver(BundledSQLiteDriver())
        .build()
}