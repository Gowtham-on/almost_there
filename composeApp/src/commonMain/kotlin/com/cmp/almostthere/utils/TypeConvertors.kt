package com.cmp.almostthere.utils

import androidx.room.TypeConverter
import com.cmp.almostthere.model.MapDetails
import com.cmp.almostthere.model.TriggerType
import com.cmp.almostthere.network.UserData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun mapDetailsToString(mapDetails: MapDetails): String =
        Json.encodeToString(mapDetails)

    @TypeConverter
    fun stringToMapDetails(string: String): MapDetails =
        Json.decodeFromString(string)

    @TypeConverter
    fun userDataToString(userData: UserData): String =
        Json.encodeToString(userData)

    @TypeConverter
    fun stringToUserData(string: String): UserData =
        Json.decodeFromString(string)

    @TypeConverter
    fun triggerTypeToString(triggerType: TriggerType): String =
        triggerType.name

    @TypeConverter
    fun stringToTriggerType(value: String): TriggerType =
        TriggerType.valueOf(value)
}
