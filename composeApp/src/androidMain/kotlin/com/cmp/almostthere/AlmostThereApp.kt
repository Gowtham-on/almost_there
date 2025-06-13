package com.cmp.almostthere

import android.app.Application
import com.cmp.almostthere.di.initKoin

class AlmostThereApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}