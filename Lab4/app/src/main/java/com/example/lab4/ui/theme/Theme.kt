package com.example.lab4.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lab4DarkColorPalette = lightColors(
    primary = lab4PrimaryColor,
    primaryVariant = lab4TertiaryColor,
    onPrimary = Color.White,
    secondary = lab4SecondaryColor
)
private val lab4LightColorPalette = darkColors(
    primary = lab4PrimaryDarkColor,
    primaryVariant = lab4TertiaryDarkColor,
    onPrimary = Color.White,
    secondary = lab4SecondaryDarkColor
)

@Composable
fun Lab4Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colors = if (darkTheme)
    {
        lab4DarkColorPalette
    }
    else
    {
        lab4LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = lab4Typography,
        shapes = lab4Shapes,
        content = content
    )
}

