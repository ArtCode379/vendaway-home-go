package vendaway.housewares.vendawayhomego.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import vendaway.housewares.vendawayhomego.R

private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val headingFont = FontFamily(
    Font(GoogleFont("DM Sans"), fontProvider, FontWeight.Normal),
    Font(GoogleFont("DM Sans"), fontProvider, FontWeight.SemiBold),
    Font(GoogleFont("DM Sans"), fontProvider, FontWeight.Bold),
)

private val bodyFont = FontFamily(
    Font(GoogleFont("Nunito"), fontProvider, FontWeight.Normal),
    Font(GoogleFont("Nunito"), fontProvider, FontWeight.SemiBold),
)

val AppTypography = Typography(
    headlineLarge = TextStyle(fontFamily = headingFont, fontWeight = FontWeight.Bold, fontSize = 30.sp),
    headlineMedium = TextStyle(fontFamily = headingFont, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    titleLarge = TextStyle(fontFamily = headingFont, fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleMedium = TextStyle(fontFamily = headingFont, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    bodyLarge = TextStyle(fontFamily = bodyFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = bodyFont, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontFamily = bodyFont, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
)

val Typography = AppTypography
