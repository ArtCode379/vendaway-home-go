package vendaway.housewares.vendawayhomego.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = BrandSurface,
    secondary = BrandAccent,
    onSecondary = BrandSurface,
    background = BrandBackground,
    onBackground = BrandText,
    surface = BrandSurface,
    onSurface = BrandText,
    surfaceVariant = BrandChip,
    onSurfaceVariant = BrandMuted,
    outline = BrandBorder,
    tertiary = BrandWarning,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFA183),
    secondary = Color(0xFF69D7CC),
    background = BrandDark,
    surface = Color(0xFF332723),
    onSurface = Color(0xFFFFF4EC),
)

@Composable
fun ProductAppOJFXTTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content,
    )
}
