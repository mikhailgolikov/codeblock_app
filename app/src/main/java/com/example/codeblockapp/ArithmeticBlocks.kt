package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



abstract class ArithmeticBlock (
    var symbol: String,
    var left: Block? = EmptyBlock(),
    var right: Block? = EmptyBlock(),
) : Block(color = Color.LightGray), IntBlock {

    var leftText: String = ""
    var rightText: String = ""

    @Composable
    override fun Create(onClick: () -> Unit) {
        super.Create(onClick)
        var leftStateText by remember { mutableStateOf(leftText) }
        var rightStateText by remember { mutableStateOf(rightText) }
        var content: @Composable BoxScope.()->Unit = {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 30.dp, vertical = 15.dp)
                    .width(150.dp),
                value = leftStateText,
                enabled = enabled,
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                ),
                onValueChange = { newText ->
                    if (enabled) {
                        leftStateText = newText
                        leftText = newText
                    }
                }
            )
        }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (left?.clickable == true) {
                    left?.modifier = left?.modifier!!.clickable{
                        inputBlock = left
                    }
                }
                left?.Create {  }

                Text(text = symbol, fontSize = 20.sp, color = Color.Black,
                    modifier = Modifier.padding(8.dp))

                right?.Create {  }

                TextField(
                    value = rightStateText,
                    enabled = enabled,
                    onValueChange = {
                        rightStateText = it
                        rightText = it
                    },
                    singleLine = true,
                    modifier = Modifier.width(80.dp)
                )
            }
        }

//        left = leftText.toIntOrNull()?.let { NumberBlock(it) } ?:
//                if (leftText.isNotEmpty()) DeclarationBlock(leftText) else null
//        right = rightText.toIntOrNull()?.let { NumberBlock(it) } ?:
//                if (rightText.isNotEmpty()) DeclarationBlock(rightText) else null
    }

}

class PlusBlock(left: Block? = null, right: Block? = null)
    : ArithmeticBlock("+", left, right), IntBlock {

    override fun toIntExpression(): IntExpression {
        val l = (left as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный левый операнд")
        val r = (right as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный правый операнд")
        return PlusExpression(l, r)
    }

    override fun Copy() : PlusBlock {
        return PlusBlock(left, right)
    }
}

class MinusBlock(left: Block?, right: Block?)
    : ArithmeticBlock("-", left, right), IntBlock {

    override fun toIntExpression(): IntExpression {
        val l = (left as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный левый операнд")
        val r = (right as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный правый операнд")
        return MinusExpression(l, r)
    }

    override fun Copy() : MinusBlock {
        return MinusBlock(left, right)
    }
}

class MultiplicationBlock(left: Block?, right: Block?)
    : ArithmeticBlock("*", left, right), IntBlock {

    override fun toIntExpression(): IntExpression {
        val l = (left as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный левый операнд")
        val r = (right as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный правый операнд")
        return MultiplicationExpression(l, r)
    }

    override fun Copy() : MultiplicationBlock {
        return MultiplicationBlock(left, right)
    }
}

class DivisionBlock(left: Block?, right: Block?)
    : ArithmeticBlock("/", left, right), IntBlock {

    override fun toIntExpression(): IntExpression {
        val l = (left as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный левый операнд")
        val r = (right as? IntBlock)?.toIntExpression()
            ?: throw IllegalStateException("Неверный правый операнд")
        return DivisionExpression(l, r)
    }

    override fun Copy() : DivisionBlock {
        return DivisionBlock(left, right)
    }
}