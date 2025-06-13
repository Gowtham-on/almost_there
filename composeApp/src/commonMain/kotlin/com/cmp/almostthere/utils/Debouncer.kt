package com.cmp.almostthere.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Debouncer(private val debounceTimeMs: Long) {
    private var debounceJob: Job? = null

    fun submit(scope: CoroutineScope, action: () -> Unit) {
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(debounceTimeMs)
            action()
        }
    }

    fun cancel() {
        debounceJob?.cancel()
    }
}