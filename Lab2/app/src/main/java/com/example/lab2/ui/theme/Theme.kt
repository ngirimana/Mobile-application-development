package com.example.lab2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val lab2DarkColorPalette = lightColors(
    primary = lab2PrimaryColor,
    primaryVariant = lab2TertiaryColor,
    onPrimary = Color.White,
    secondary = lab2SecondaryColor
)
private val lab2LightColorPalette = darkColors(
    primary = lab2PrimaryDarkColor,
    primaryVariant = lab2TertiaryDarkColor,
    onPrimary = Color.White,
    secondary = lab2SecondaryDarkColor
)

@Composable
fun Lab2Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colors = if (darkTheme)
    {
        lab2DarkColorPalette
    }
    else
    {
        lab2LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = lab2Typography,
        shapes = lab2Shapes,
        content = content
    )
}