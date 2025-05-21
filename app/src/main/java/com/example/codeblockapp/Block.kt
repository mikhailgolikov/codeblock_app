package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

open class Block(
    val text: String = "empty",
    val color: Color = Color.LightGray,
    val minWidth: Dp = 100.dp,
    val minHeight: Dp = 50.dp,
    var modifier: Modifier = Modifier
        .padding(10.dp)
        .clip(CircleShape)
        .defaultMinSize(minWidth, minHeight)
        .width(IntrinsicSize.Max)
        .height(IntrinsicSize.Max)
        .background(color)
) {
    @Composable
    open fun Create(
        onClick: () -> Unit
    ) {}
}

class VariableBlock : Block() {
//    override fun Create(onClick: () -> Unit) {
//
//    }
}


//@Composable
//fun Block(
//    text: String = "empty",
//    color: Color = Color.LightGray,
//    minWidth: Dp = 100.dp,
//    minHeight: Dp = 50.dp,
//    modifier: Modifier = Modifier
//        .padding(10.dp)
//        .clip(CircleShape)
//        .defaultMinSize(minWidth, minHeight)
//        .width(IntrinsicSize.Max)
//        .height(IntrinsicSize.Max)
//        .background(color),
//    content: @Composable ()->Unit
//) {}
//
//@Composable
//fun VariableBlock() {
//    Block {
//        Box() {}
//    }
//}

