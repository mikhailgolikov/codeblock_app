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
import com.example.codeblockapp.IfExpression.OutputExpression


open class Block(
    val text: String,
    var color: Color
) {
    val minWidth: Dp = 100.dp
    val minHeight: Dp = 50.dp
    var modifier: Modifier = Modifier
        .padding(10.dp)
        .clip(CircleShape)
        .defaultMinSize(minWidth, minHeight)
        .width(IntrinsicSize.Max)
        .height(IntrinsicSize.Max)
        .background(color)

    // создаю базовую функцию, которую буду переопределять
    open fun toExpression(): UnitExpression? {
        return null
    }

    @Composable
    // фронт
    // функция криэйт, это Влад похоже делал
    open fun Create(
        onClick: () -> Unit
    ) {
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}

interface IntBlock {
    fun toIntExpression(): IntExpression
}

class NumberBlock(
    var number: Int = 0
) : Block("Число", Color.Cyan) {
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

class VariableBlock(
    var name: String = ""
) : Block("Переменная", Color.Yellow) {
    fun toIntExpression(): IntExpression {
        return VariableExpression(name)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        //// здесь фронт надо писать
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}




class PlusBlock(
    var left: Block? = null,
    var right: Block? = null
) : Block("Сложение", Color.Green) {
    fun toIntExpression(): IntExpression? {
        val l = (left as? IntBlock)?.toIntExpression()
        val r = (right as? IntBlock)?.toIntExpression()
        if (l != null && r != null) return PlusExpression(l, r)
        return null
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт (выбор 2 блоков)
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}


class MinusBlock(
    var left: Block? = null,
    var right: Block? = null
) : Block("Вычитание", Color.Red) {
    fun toIntExpression(): IntExpression? {
        val l = (left as? IntBlock)?.toIntExpression()
        val r = (right as? IntBlock)?.toIntExpression()
        if (l != null && r != null) return MinusExpression(l, r)
        return null
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}

class MultiplicationBlock(
    var left: Block? = null,
    var right: Block? = null
) : Block("Умножение", Color.Magenta) {
    fun toIntExpression(): IntExpression? {
        val l = (left as? IntBlock)?.toIntExpression()
        val r = (right as? IntBlock)?.toIntExpression()
        if (l != null && r != null) return MultiplicationExpression(l, r)
        return null
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}


class DivisionBlock(
    var left: Block? = null,
    var right: Block? = null
) : Block("Деление", Color.Blue) {
    fun toIntExpression(): IntExpression? {
        val l = (left as? IntBlock)?.toIntExpression()
        val r = (right as? IntBlock)?.toIntExpression()
        if (l != null && r != null) return DivisionExpression(l, r)
        return null
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}


// Объявление переменной
class DeclarationBlock(
    var name: String = "",
    var value: Block? = null
) : Block("Объявление", Color.LightGray) {
    override fun toExpression(): UnitExpression? {
        val intValue = (value as? IntBlock)?.toIntExpression() ?: return null
        return DeclarationExpression(name, intValue)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}

// Присваивание
class AssignmentBlock(
    var name: String = "",
    var value: Block? = null
) : Block("Присваивание", Color.LightGray) {
    override fun toExpression(): UnitExpression? {
        val intValue = (value as? IntBlock)?.toIntExpression() ?: return null
        return AssignmentExpression(name, intValue)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт
        Box(
            modifier = modifier
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White)
        }
    }
}

class OutputBlock(
    var value: Block? = null
) : Block("Вывод", Color(0xFF6A5ACD)) {

    override fun toExpression(): UnitExpression? {
        val valueExpr = (value as? IntBlock)?.toIntExpression() ?: return null
        return OutputExpression(valueExpr)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        // здесь фронт (одно поле под переменную/число)
        Box(
            modifier = modifier.clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text("cout", fontSize = 16.sp, color = Color.White)
        }
    }
}


fun NumberBlock.toIntBlock() = object : IntBlock {
    override fun toIntExpression(): IntExpression = NumberExpression(number)
}

fun VariableBlock.toIntBlock() = object : IntBlock {
    override fun toIntExpression(): IntExpression = VariableExpression(name)
}

fun PlusBlock.toIntBlock() = object : IntBlock {
    override fun toIntExpression(): IntExpression = PlusExpression(
        (left as IntBlock).toIntExpression(),
        (right as IntBlock).toIntExpression()
    )
}
fun MinusBlock.toIntBlock() = object : IntBlock {
    override fun toIntExpression(): IntExpression = MinusExpression(
        (left as IntBlock).toIntExpression(),
        (right as IntBlock).toIntExpression()
    )
}

fun MultiplicationBlock.toIntBlock() = object : IntBlock {
    override fun toIntExpression(): IntExpression = MultiplicationExpression(
        (left as IntBlock).toIntExpression(),
        (right as IntBlock).toIntExpression()
    )
}

fun DivisionBlock.toIntBlock() = object : IntBlock {
    override fun toIntExpression(): IntExpression = DivisionExpression(
        (left as IntBlock).toIntExpression(),
        (right as IntBlock).toIntExpression()
    )
}

//class VariableBlock : Block() {
//    override fun Create(onClick: () -> Unit) {
//
//    }
//}


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

