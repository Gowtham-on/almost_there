package com.cmp.almostthere.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.cmp.almostthere.model.MapDetails
import com.cmp.almostthere.model.TriggerType
import com.cmp.almostthere.network.FirebaseApiImpl
import com.cmp.almostthere.network.UserData
import com.hoc081098.kmp.viewmodel.ViewModel
import kotlinx.coroutines.launch

class TriggerViewmodel : ViewModel() {

    var destinationPlace: MapDetails by mutableStateOf(MapDetails())
        private set
    var triggerType: TriggerType by mutableStateOf(TriggerType.NONE)
        private set
    var message: String by mutableStateOf("")
        private set
    var receiverData: UserData by mutableStateOf(UserData())
        private set

    fun setUserDestination(place: MapDetails) {
        destinationPlace = place
    }

    fun setUserTriggerType(type: TriggerType) {
        triggerType = type
    }


    fun setUserMessage(message: String) {
        this.message = message
    }


    var showAlertDialog: Boolean by mutableStateOf(false)


    fun searchUser(userId: String) {
        viewModelScope.launch {
            val userData = FirebaseApiImpl.loadUserFromId(userId)

            Logger.d { userData?.name.toString() }
            if (userData != null) {

                receiverData = userData
                showAlertDialog = true
            }
        }
    }

    fun clearReceiverData() {
        receiverData = UserData()
    }
}