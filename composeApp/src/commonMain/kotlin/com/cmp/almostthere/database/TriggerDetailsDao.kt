package com.cmp.almostthere.database

import androidx.compose.runtime.Composable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.cmp.almostthere.model.TriggerDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface TriggerDetailsDao {

    @Upsert()
    suspend fun insertTriggerDetails(triggerDetails: TriggerDetails)

    @Query("SELECT * FROM TriggerDetails")
    fun getTriggerDetails():  Flow<List<TriggerDetails>>

    @Delete
    suspend fun deleteTriggerDetails(triggerDetails: TriggerDetails)

}

@Composable
expect fun getTriggerDao(): TriggerDetailsDao