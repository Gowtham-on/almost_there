package com.cmp.almostthere.utils


expect fun getUserId(): String?
expect fun getDeviceId(): String?

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM
}