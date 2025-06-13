package com.cmp.almostthere

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform