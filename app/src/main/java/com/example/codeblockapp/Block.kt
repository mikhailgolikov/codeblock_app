package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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

    constructor() {}

    constructor(color: Color) {
        this.color = color
        this.modifier = Modifier
            .padding(10.dp)
            .clip(CircleShape)
            .defaultMinSize(minWidth, minHeight)
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .background(color)
    }

    @Composable
    // фронт
    open fun Create(
        onClick: () -> Unit
    ) {
        if (clickable) {
            modifier = modifier.clickable(onClick = onClick)
        }
    }

    open fun Copy() : Block{
        return Block()
    }
}


interface UnitBlock {
    fun toUnitExpression(): UnitExpression?
}

interface IntBlock {
    fun toIntExpression(): IntExpression?
}



class DeclarationBlock(var name: String = "")
    : Block(color = Color.Red), UnitBlock {

    override fun toUnitExpression(): UnitExpression? {
        return DeclarationExpression(name)
    }

    @Composable
    override fun Create(
        onClick: ()->Unit
    ) {
        super.Create(onClick)

        var stateName by remember { mutableStateOf(name) }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 30.dp, vertical = 15.dp)
                    .width(150.dp),
                value = stateName,
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
                        stateName = newText
                        name = newText
                    }
                }
            )
        }
    }

    override fun Copy() : DeclarationBlock {
        return DeclarationBlock(name = name)
    }
}

class AssignmentBlock (
    var name: String = "",
    var value: Block? = null,
) : Block(color = Color.Green), UnitBlock {

    override fun toUnitExpression(): UnitExpression? {
        val intValue = (value as? IntBlock)?.toIntExpression() ?: return null
        return AssignmentExpression(name, intValue)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        super.Create(onClick)

        var stateName by remember { mutableStateOf(name) }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                TextField(
                    value = stateName,
                    enabled = enabled,
                    onValueChange = {
                        stateName = it
                        name = it
                    },
                    singleLine = true,
                    modifier = Modifier.width(80.dp)
                )

                Text(text = "=", fontSize = 20.sp, color = Color.Black,
                    modifier = Modifier.padding(8.dp))

                Box(
                    modifier = Modifier.width(80.dp)
                )
            }
        }
    }

    override fun Copy() : AssignmentBlock {
        return AssignmentBlock(name = name, value = value)
    }
}






abstract class ArithmeticBlock (
    var symbol: String,
    var left: Block? = null,
    var right: Block? = null,
) : Block(color = Color.LightGray), IntBlock {

    var leftText: String = ""
    var rightText: String = ""

    @Composable
    override fun Create(onClick: () -> Unit) {
        super.Create(onClick)

        var leftStateText by remember { mutableStateOf(leftText) }
        var rightStateText by remember { mutableStateOf(rightText) }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                TextField(
                    value = leftStateText,
                    enabled = enabled,
                    onValueChange = {
                        leftStateText = it
                        leftText = it
                    },
                    singleLine = true,
                    modifier = Modifier.width(80.dp)
                )

                Text(text = symbol, fontSize = 20.sp, color = Color.Black,
                    modifier = Modifier.padding(8.dp))


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

        left = leftText.toIntOrNull()?.let { NumberBlock(it) } ?:
                if (leftText.isNotEmpty()) DeclarationBlock(leftText) else null
        right = rightText.toIntOrNull()?.let { NumberBlock(it) } ?:
                if (rightText.isNotEmpty()) DeclarationBlock(rightText) else null
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





class NumberBlock(
    var number: Int = 0
) : Block(Color.Cyan) {
    fun toIntExpression(): IntExpression {
        return NumberExpression(number)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}




//class PlusBlock(
//    var left: Block? = null,
//    var right: Block? = null
//) : Block(Color.Green, "Сложение") {
//    fun toIntExpression(): IntExpression? {
//        val l = (left as? IntBlock)?.toIntExpression()
//        val r = (right as? IntBlock)?.toIntExpression()
//        if (l != null && r != null) return PlusExpression(l, r)
//        return null
//    }
//
//    @Composable
//    override fun Create(onClick: () -> Unit) {
//        // здесь фронт (выбор 2 блоков)
//        Box(
//            modifier = modifier
//                .clickable { onClick() },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text, fontSize = 16.sp, color = Color.White)
//        }
//    }
//}
//
//
//class MinusBlock(
//    var left: Block? = null,
//    var right: Block? = null
//) : Block(Color.Red, "Вычитание") {
//    fun toIntExpression(): IntExpression? {
//        val l = (left as? IntBlock)?.toIntExpression()
//        val r = (right as? IntBlock)?.toIntExpression()
//        if (l != null && r != null) return MinusExpression(l, r)
//        return null
//    }
//
//    @Composable
//    override fun Create(onClick: () -> Unit) {
//        // здесь фронт
//        Box(
//            modifier = modifier
//                .clickable { onClick() },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text, fontSize = 16.sp, color = Color.White)
//        }
//    }
//}
//
//class MultiplicationBlock(
//    var left: Block? = null,
//    var right: Block? = null
//) : Block(Color.Magenta, "Умножение") {
//    fun toIntExpression(): IntExpression? {
//        val l = (left as? IntBlock)?.toIntExpression()
//        val r = (right as? IntBlock)?.toIntExpression()
//        if (l != null && r != null) return MultiplicationExpression(l, r)
//        return null
//    }
//
//    @Composable
//    override fun Create(onClick: () -> Unit) {
//        // здесь фронт
//        Box(
//            modifier = modifier
//                .clickable { onClick() },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text, fontSize = 16.sp, color = Color.White)
//        }
//    }
//}
//
//
//class DivisionBlock(
//    var left: Block? = null,
//    var right: Block? = null
//) : Block(Color.Blue, "Деление") {
//    fun toIntExpression(): IntExpression? {
//        val l = (left as? IntBlock)?.toIntExpression()
//        val r = (right as? IntBlock)?.toIntExpression()
//        if (l != null && r != null) return DivisionExpression(l, r)
//        return null
//    }
//
//    @Composable
//    override fun Create(onClick: () -> Unit) {
//        // здесь фронт
//        Box(
//            modifier = modifier
//                .clickable { onClick() },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text, fontSize = 16.sp, color = Color.White)
//        }
//    }
//}
//
//
//// Объявление переменной
//class DeclarationBlock (
//    var name: String = "",
//    var value: Block? = null
//) : Block(Color.LightGray, "Объявление"), UnitBlock {
//    override fun toExpression(): UnitExpression? {
//        val intValue = (value as? IntBlock)?.toIntExpression() ?: return null
//        return DeclarationExpression(name, intValue)
//    }
//
//    @Composable
//    override fun Create(onClick: () -> Unit) {
//        // здесь фронт
//        Box(
//            modifier = modifier
//                .clickable { onClick() },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text, fontSize = 16.sp, color = Color.White)
//        }
//    }
//}
//
//// Присваивание
//class AssignmentBlock(
//    var name: String = "",
//    var value: Block? = null
//) : Block(Color.LightGray, "Присваивание") {
//    override fun toExpression(): UnitExpression? {
//        val intValue = (value as? IntBlock)?.toIntExpression() ?: return null
//        return AssignmentExpression(name, intValue)
//    }
//
//    @Composable
//    override fun Create(onClick: () -> Unit) {
//        // здесь фронт
//        Box(
//            modifier = modifier
//                .clickable { onClick() },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text, fontSize = 16.sp, color = Color.White)
//        }
//    }
//}
//
//
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

