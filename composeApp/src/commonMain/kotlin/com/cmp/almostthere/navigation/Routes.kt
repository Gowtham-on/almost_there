package com.cmp.almostthere.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Main : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Triggers : Routes()

    @Serializable
    data object TriggerForm : Routes()

    @Serializable
    data object Settings : Routes()

    @Serializable
    data object Splash : Routes()

    @Serializable
    data object ThemeChangeView : Routes()

}