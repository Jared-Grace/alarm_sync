package com.example.alarmsync.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

private val base = Typography()

val Typography = Typography(
        displayLarge = base.displayLarge.copy(fontSize = 40.sp),
        displayMedium = base.displayMedium.copy(fontSize = 36.sp),
        displaySmall = base.displaySmall.copy(fontSize = 32.sp),

        headlineLarge = base.headlineLarge.copy(fontSize = 30.sp),
        headlineMedium = base.headlineMedium.copy(fontSize = 26.sp),
        headlineSmall = base.headlineSmall.copy(fontSize = 22.sp),

        titleLarge = base.titleLarge.copy(fontSize = 20.sp),
        titleMedium = base.titleMedium.copy(fontSize = 18.sp),
        titleSmall = base.titleSmall.copy(fontSize = 16.sp),

        bodyLarge = base.bodyLarge.copy(fontSize = 18.sp),
        bodyMedium = base.bodyMedium.copy(fontSize = 16.sp),
        bodySmall = base.bodySmall.copy(fontSize = 14.sp),

        labelLarge = base.labelLarge.copy(fontSize = 14.sp),
        labelMedium = base.labelMedium.copy(fontSize = 12.sp),
        labelSmall = base.labelSmall.copy(fontSize = 11.sp)
)