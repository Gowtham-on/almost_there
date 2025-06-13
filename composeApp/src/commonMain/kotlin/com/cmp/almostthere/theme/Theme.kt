package com.cmp.almostthere.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = black,
    secondary = blackGray,
    background = blackBg,
    surface = whiteBg,
    tertiary = darkGray,
    inverseSurface = darkBorder
)

private val LightColorScheme = lightColorScheme(
    primary = whiteBg,
    secondary = lightGray,
    background = whiteBg,
    surface = black,
    tertiary = whiteGray,
    inverseSurface = lightBorder

)

@Composable
fun AlmostThereTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}