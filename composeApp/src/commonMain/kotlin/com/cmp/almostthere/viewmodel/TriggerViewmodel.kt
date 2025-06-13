package com.cmp.almostthere.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cmp.almostthere.model.MapDetails
import com.cmp.almostthere.model.NotifyType
import com.cmp.almostthere.model.TriggerType
import com.devtamuno.kmp.contactpicker.data.Contact
import com.hoc081098.kmp.viewmodel.ViewModel

class TriggerViewmodel : ViewModel() {

    var selectedContact: Contact? by mutableStateOf(null)
        private set
    var destinationPlace: MapDetails by mutableStateOf(MapDetails())
        private set
    var triggerType: TriggerType by mutableStateOf(TriggerType.NONE)
        private set
    var message: String by mutableStateOf("")
        private set
    var deliveryMethod: NotifyType by mutableStateOf(NotifyType.NONE)
        private set

    fun setUserDestination(place: MapDetails) {
        destinationPlace = place
    }

    fun setUserTriggerType(type: TriggerType) {
        triggerType = type
    }

    fun setContact(contact: Contact) {
        selectedContact = contact
    }

    fun setUserMessage(message: String) {
        this.message = message
    }

    fun setUserDeliveryMethod(method: NotifyType) {
        deliveryMethod = method
    }
}