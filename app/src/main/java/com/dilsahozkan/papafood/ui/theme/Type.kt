package com.dilsahozkan.papafood.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dilsahozkan.papafood.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val mediumFont: FontFamily = FontFamily(Font(R.font.poppins_medium, weight = FontWeight.W400))
val semiBold: FontFamily = FontFamily(Font(R.font.poppins_semibold, weight = FontWeight.Bold))
val regular: FontFamily = FontFamily(Font(R.font.poppins_regular, weight = FontWeight.Normal))