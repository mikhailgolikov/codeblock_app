package com.example.codeblockapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

class NumberBlock(
    var number: Int = 0
) : Block(Color.Cyan) {
    fun toIntExpression(): IntExpression {
        return NumberExpression(number)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        Box(
            modifier = modifier.clickable { onClick() }, contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}