package com.example.alarmsync.ui.theme
import androidx.compose.ui.unit.sp

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
        displayLarge = Typography().displayLarge.copy(fontSize = 40.sp),
displayMedium = Typography().displayMedium.copy(fontSize = 36.sp),
displaySmall = Typography().displaySmall.copy(fontSize = 32.sp),

headlineLarge = Typography().headlineLarge.copy(fontSize = 30.sp),
headlineMedium = Typography().headlineMedium.copy(fontSize = 26.sp),
headlineSmall = Typography().headlineSmall.copy(fontSize = 22.sp),

titleLarge = Typography().titleLarge.copy(fontSize = 20.sp),
titleMedium = Typography().titleMedium.copy(fontSize = 18.sp),
titleSmall = Typography().titleSmall.copy(fontSize = 16.sp),

bodyLarge = Typography().bodyLarge.copy(fontSize = 18.sp),
bodyMedium = Typography().bodyMedium.copy(fontSize = 16.sp),
bodySmall = Typography().bodySmall.copy(fontSize = 14.sp),

labelLarge = Typography().labelLarge.copy(fontSize = 14.sp),
labelMedium = Typography().labelMedium.copy(fontSize = 12.sp),
labelSmall = Typography().labelSmall.copy(fontSize = 11.sp)
)