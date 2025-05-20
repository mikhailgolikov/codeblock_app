package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset

data class Block(
    val text: String = "Блок",
    val color: Color = Color.LightGray,
    val minWidth: Dp = 100.dp,
    val minHeight: Dp = 50.dp
) {
    @Composable
    fun Create(
        initialOffset: Offset = Offset.Zero,
        onOffsetChange: (Offset) -> Unit = {},
        onClick: () -> Unit = {}
    ) {
        var offset by remember { mutableStateOf(initialOffset) }

        val transformState = rememberTransformableState { _, offsetChange, _ ->
            offset += offsetChange
            onOffsetChange(offset)
        }

        Box(
            modifier = Modifier
//                .graphicsLayer(
//                    translationX = offset.x,
//                    translationY = offset.y
//                )
                .transformable(state = transformState)
                .padding(10.dp)
                .clip(CircleShape)
                .defaultMinSize(minWidth, minHeight)
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .background(color)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 20.dp),
                color = Color.White
            )
        }
    }
}