package com.example.codeblockapp

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

class EmptyBlock(
    var contentAlignment: Alignment = Alignment.Center,
) : Block(color = Color.White) {
    @Composable
    override fun Create(onClick: ()->Unit) {
        Box(modifier = modifier)
    }
}