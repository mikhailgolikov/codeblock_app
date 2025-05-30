package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeblockapp.IfExpression.OutputExpression


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


interface UnitBlock {
    fun toUnitExpression(): UnitExpression?
}

interface IntBlock {
    fun toIntExpression(): IntExpression?
}


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


//fun NumberBlock.toIntBlock() = object : IntBlock {
//    override fun toIntExpression(): IntExpression = NumberExpression(number)
//}
//
//fun VariableBlock.toIntBlock() = object : IntBlock {
//    override fun toIntExpression(): IntExpression = VariableExpression(name)
//}
//
//fun PlusBlock.toIntBlock() = object : IntBlock {
//    override fun toIntExpression(): IntExpression = PlusExpression(
//        (left as IntBlock).toIntExpression(),
//        (right as IntBlock).toIntExpression()
//    )
//}
//fun MinusBlock.toIntBlock() = object : IntBlock {
//    override fun toIntExpression(): IntExpression = MinusExpression(
//        (left as IntBlock).toIntExpression(),
//        (right as IntBlock).toIntExpression()
//    )
//}
//
//fun MultiplicationBlock.toIntBlock() = object : IntBlock {
//    override fun toIntExpression(): IntExpression = MultiplicationExpression(
//        (left as IntBlock).toIntExpression(),
//        (right as IntBlock).toIntExpression()
//    )
//}
//
//fun DivisionBlock.toIntBlock() = object : IntBlock {
//    override fun toIntExpression(): IntExpression = DivisionExpression(
//        (left as IntBlock).toIntExpression(),
//        (right as IntBlock).toIntExpression()
//    )
//}

