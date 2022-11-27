package com.example.lab3.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lab3DarkColorPalette = lightColors(
    primary = lab3PrimaryColor,
    primaryVariant = lab3TertiaryColor,
    onPrimary = Color.White,
    secondary = lab3SecondaryColor
)
private val lab3LightColorPalette = darkColors(
    primary = lab3PrimaryDarkColor,
    primaryVariant = lab3TertiaryDarkColor,
    onPrimary = Color.White,
    secondary = lab3SecondaryDarkColor
)

@Composable
fun Lab3Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colors = if (darkTheme)
    {
        lab3DarkColorPalette
    }
    else
    {
        lab3LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = lab3Typography,
        shapes = lab3Shapes,
        content = content
    )
}