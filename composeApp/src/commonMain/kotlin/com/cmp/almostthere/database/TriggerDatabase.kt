package com.cmp.almostthere.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cmp.almostthere.model.TriggerDetails
import com.cmp.almostthere.utils.Converters

@Database (
    entities = [TriggerDetails::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TriggerDatabase: RoomDatabase() {

    abstract fun triggerDetailsDao(): TriggerDetailsDao
}