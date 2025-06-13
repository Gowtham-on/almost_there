package com.cmp.almostthere.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.devtamuno.kmp.contactpicker.data.Contact
import com.hoc081098.kmp.viewmodel.ViewModel

class TriggerViewmodel : ViewModel() {

    var selectedContact: Contact? by mutableStateOf(null)
        private set

    fun setContact(contact: Contact) {
        selectedContact = contact
    }


}