package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


open class Block {
    var color: Color = Color.White
    var text: String = ""
    var clickable: Boolean = true
        set(value) {
            field = value
            enabled = !value
        }
    var enabled: Boolean = false
    val minWidth: Dp = 100.dp
    val minHeight: Dp = 50.dp
    var modifier: Modifier = Modifier
    var inputBlock: Block? = null

    constructor() {}

    constructor(color: Color) {
        this.color = color
        this.modifier =
            Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .defaultMinSize(minWidth, minHeight)
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .background(color)
    }

    @Composable
    open fun Create(
        onClick: () -> Unit
    ) {
        if (clickable) {
            modifier = modifier.clickable(onClick = onClick)
        }
    }

    open fun Copy(): Block {
        return Block()
    }
}