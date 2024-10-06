package com.example.cupcake.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.pink_400,
    primaryContainer = AppColors.pink_950,
    secondary = AppColors.purple_400,
    secondaryContainer = AppColors.purple_400,
    surfaceTint = AppColors.pink_400,
    tertiary = AppColors.purple_400,
    onPrimary = AppColors.black,
    onSecondary = AppColors.black,
    onSurface = AppColors.white,
    surfaceContainer = AppColors.black,
    surfaceContainerHighest = AppColors.black
)
private val LightColorScheme = lightColorScheme(
    primary = AppColors.pink_600,
    primaryContainer = AppColors.pink_950,
    secondary = AppColors.purple_400,
    secondaryContainer = AppColors.purple_400,
    surfaceTint = AppColors.purple_700,
    tertiary = AppColors.purple_700,
    onPrimary = AppColors.white,
    onSecondary = AppColors.black,
    onSurface = AppColors.white,
    surfaceContainer = AppColors.black,
    surfaceContainerHighest = AppColors.pink_600
)

@Composable
fun CupCakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity  = view.context as Activity
            activity.window.navigationBarColor = colorScheme.surfaceContainer.toArgb()
            activity.window.statusBarColor = colorScheme.primaryContainer.toArgb()
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}