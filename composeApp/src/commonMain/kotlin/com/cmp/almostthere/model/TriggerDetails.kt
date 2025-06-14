package com.cmp.almostthere.model

import com.devtamuno.kmp.contactpicker.data.Contact

data class TriggerDetails(
    val name: String,
    val contacts: List<Contact>,
    val distance: String,
    val triggerType: TriggerType,
    val message: String,
    var location: MapDetails
)

enum class  TriggerType {
    NEAR_DESTINATION,
    CUSTOM_TIME_AWAY,
    NONE
}

enum class NotifyType {
    WHATSAPP,
    SMS,
    NONE
}