package com.cmp.almostthere.network

import com.cmp.almostthere.FirebaseDatabaseApiImpl

actual fun getFirebaseDatabaseApi(): FirebaseDatabaseApi {
    return FirebaseDatabaseApiImpl()
}