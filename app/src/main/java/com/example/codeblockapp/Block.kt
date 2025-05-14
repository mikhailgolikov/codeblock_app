package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


class Block {
    var minWidth: Dp = 100.dp
    var minHeight: Dp = 50.dp
    var color: Color = Color.LightGray
//    var modifier: Modifier = Modifier
//    var mobile: Boolean = false
//        get() = field
//        set(value) {
//            if (value) minWidth = 0.dp
//        }



    @Composable
    fun Create() {
        var offset by remember { mutableStateOf(Offset.Zero)}
        val state = rememberTransformableState {
                scaleChange, offsetChange, rotationChange -> offset += offsetChange
        }
        Box(modifier = Modifier
            .graphicsLayer(
                translationX = offset.x,
                translationY = offset.y
            )
            .transformable(state = state)
            .padding(10.dp)
            .clip(shape = CircleShape)
            .defaultMinSize(minWidth, minHeight)
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .background(color = color),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "something",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
    }
}