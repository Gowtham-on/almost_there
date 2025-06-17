package com.cmp.almostthere.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cmp.almostthere.model.MapDetails
import com.cmp.almostthere.model.TriggerDetails
import com.cmp.almostthere.model.TriggerType
import com.cmp.almostthere.network.FirebaseApiImpl
import com.cmp.almostthere.network.UserData
import com.hoc081098.kmp.viewmodel.ViewModel
import kotlinx.coroutines.launch

class TriggerViewmodel : ViewModel() {

    var currentUserData: UserData by mutableStateOf(UserData())
        private set

    fun setCurrentUserInfoData(userData: UserData) {
        currentUserData = userData
    }

    var userId = ""

    var destinationPlace: MapDetails by mutableStateOf(MapDetails())
        private set
    var triggerType: TriggerType by mutableStateOf(TriggerType.NONE)
        private set
    var message: String by mutableStateOf("")
        private set
    var receiverData: UserData by mutableStateOf(UserData())
        private set

    var showAlertDialog: Boolean by mutableStateOf(false)
    var showIncorrectId: Boolean by mutableStateOf(false)

    fun setUserDestination(place: MapDetails) {
        destinationPlace = place
    }

    fun setUserTriggerType(type: TriggerType) {
        triggerType = type
    }

    fun setUserMessage(message: String) {
        this.message = message
    }

    fun searchUser(userId: String) {
        viewModelScope.launch {
            val userData = FirebaseApiImpl.loadUserFromId(userId)
            if (userData != null) {
                receiverData = userData
                showAlertDialog = true
                showIncorrectId = false
                setDialogTexts(
                    "Receiver Details",
                    "Username: ${receiverData.name}\nId: ${receiverData.userId}"
                )
            } else {
                showIncorrectId = true
            }
        }
    }

    fun clearReceiverData() {
        receiverData = UserData()
    }


    fun updateName(name: String) {
        viewModelScope.launch {
            FirebaseApiImpl.updateUser(currentUserData.userId, currentUserData.token, name)
        }
    }

    fun getTriggerDetails(): TriggerDetails {
        val triggerDetails = TriggerDetails(
            userId = userId,
            triggerType = triggerType,
            message = message,
            location = destinationPlace,
            receiverDetails = receiverData
        )
        return triggerDetails
    }

    // Alert dialog texts
    var alertDialogTitle: String by mutableStateOf("")
        private set
    var alertDialogDescription: String by mutableStateOf("")
        private set
    var confirmButtonText: String by mutableStateOf("Confirm")
        private set
    var dismissButtonText: String by mutableStateOf("Cancel")
        private set

    fun setDialogTexts(
        title: String,
        description: String,
        confirm: String = "Confirm",
        dismiss: String = "Cancel"
    ) {
        alertDialogTitle = title
        alertDialogDescription = description
        confirmButtonText = confirm
        dismissButtonText = dismiss
    }
}