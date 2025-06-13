package com.cmp.almostthere.utils

data class UiState<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: T? = null,
)
