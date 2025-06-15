package com.cmp.almostthere.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmp.almostthere.network.UserData

@Entity(tableName = "triggerDetails")
data class TriggerDetails(
    @PrimaryKey(autoGenerate = false) val userId: String = "",
    val triggerType: TriggerType,
    val message: String,
    var location: MapDetails,
    var receiverDetails: UserData,
    var isEnabled: Boolean = true,
)

enum class TriggerType {
    NEAR_DESTINATION,
    CUSTOM_TIME_AWAY,
    NONE
}
