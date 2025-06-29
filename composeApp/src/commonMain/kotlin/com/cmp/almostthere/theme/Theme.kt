package com.cmp.almostthere.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.cmp.almostthere.utils.Theme
import com.cmp.almostthere.viewmodel.TriggerViewmodel
import com.hoc081098.kmp.viewmodel.koin.compose.koinKmpViewModel

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
    val triggerViewmodel = koinKmpViewModel<TriggerViewmodel>()

    val themePreference = when (triggerViewmodel.currentTheme) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        Theme.SYSTEM -> isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = if (themePreference) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}