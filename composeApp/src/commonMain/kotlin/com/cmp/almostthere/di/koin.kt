package com.cmp.almostthere.di

import com.cmp.almostthere.viewmodel.TriggerViewmodel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val appModule = module {
    factoryOf(::TriggerViewmodel)
}


fun initKoin() {
    startKoin {
        modules(
            appModule
        )
    }
}
