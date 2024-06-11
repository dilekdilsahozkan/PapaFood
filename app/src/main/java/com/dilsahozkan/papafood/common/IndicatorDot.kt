package com.dilsahozkan.papafood.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dilsahozkan.papafood.ui.theme.MainColor
import com.dilsahozkan.papafood.ui.theme.SoftOrangeColor

@Composable
fun IndicatorDot(isSelected: Boolean, modifier: Modifier) {
    val animatedSize by animateDpAsState(
        targetValue = if (isSelected) 12.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = modifier
            .padding(2.dp)
            .clip(CircleShape)
            .size(animatedSize)
            .background(if (isSelected) MainColor else SoftOrangeColor)
    )
}