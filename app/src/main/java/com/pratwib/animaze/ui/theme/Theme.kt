package com.pratwib.animaze.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Yellow200,
    primaryVariant = Yellow800,
    secondary = Red200
)

private val LightColorPalette = lightColors(
    primary = Yellow500,
    primaryVariant = Yellow800,
    secondary = Red200
)

@Composable
fun AnimazeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}