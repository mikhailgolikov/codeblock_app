package com.example.codeblockapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.codeblockapp.IfExpression.OutputExpression

class OutputBlock(
    var value: Block? = null
) : Block(Color(0xFF6A5ACD)), UnitBlock {

    override fun toUnitExpression(): UnitExpression? {
        val valueExpr = (value as? IntBlock)?.toIntExpression() ?: return null
        return OutputExpression(valueExpr)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        super.Create(onClick)

    }
}