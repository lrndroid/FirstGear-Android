package com.firstgear.telemetry.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val JetBrainsMono = FontFamily.Monospace
val SpaceGrotesk = FontFamily.SansSerif
val GeistFont = FontFamily.SansSerif

val DisplayHero = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.Bold,
    fontSize = 56.sp,
    lineHeight = 60.sp,
    letterSpacing = (-0.04).em,
    color = OnSurfaceWhite
)

val DisplayHeroMobile = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.Bold,
    fontSize = 44.sp,
    lineHeight = 48.sp,
    letterSpacing = (-0.03).em,
    color = OnSurfaceWhite
)

val HeadlineLg = TextStyle(
    fontFamily = SpaceGrotesk,
    fontWeight = FontWeight.SemiBold,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = (-0.02).em,
    color = OnSurfaceHighLuminance
)

val HeadlineLgMobile = TextStyle(
    fontFamily = SpaceGrotesk,
    fontWeight = FontWeight.SemiBold,
    fontSize = 26.sp,
    lineHeight = 32.sp,
    letterSpacing = (-0.02).em,
    color = OnSurfaceHighLuminance
)

val HeadlineMd = TextStyle(
    fontFamily = SpaceGrotesk,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = (-0.01).em,
    color = OnSurfaceHighLuminance
)

val HeadlineSm = TextStyle(
    fontFamily = SpaceGrotesk,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    color = OnSurfaceHighLuminance
)

val BodyLg = TextStyle(
    fontFamily = GeistFont,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    color = OnSurfaceHighLuminance
)

val BodyMd = TextStyle(
    fontFamily = GeistFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    color = OnSurfaceHighLuminance
)

val BodySm = TextStyle(
    fontFamily = GeistFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    color = OnSurfaceSlate
)

val LabelTelemetryLg = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.02.em,
    color = ElectricEmeraldDim
)

val LabelTelemetrySm = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.05.em,
    color = OnSurfaceSlate
)

val LabelCaps = TextStyle(
    fontFamily = JetBrainsMono,
    fontWeight = FontWeight.Bold,
    fontSize = 10.sp,
    lineHeight = 12.sp,
    letterSpacing = 0.08.em,
    color = OnSurfaceSlate
)

val FirstGearTypography = Typography(
    headlineLarge = HeadlineLg,
    headlineMedium = HeadlineMd,
    headlineSmall = HeadlineSm,
    bodyLarge = BodyLg,
    bodyMedium = BodyMd,
    bodySmall = BodySm,
    labelLarge = LabelTelemetryLg,
    labelMedium = LabelTelemetrySm,
    labelSmall = LabelCaps
)
