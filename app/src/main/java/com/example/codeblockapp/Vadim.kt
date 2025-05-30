package com.example.codeblockapp


interface Expression<T> {
    fun interpret(context: MutableMap<String, Int>): T
}

interface IntExpression : Expression<Int> {

}

interface UnitExpression : Expression<Unit> {

}

class NumberExpression(private val number: Int) : IntExpression {

    override fun interpret(context: MutableMap<String, Int>): Int {
        return number
    }

}

class VariableExpression(private val name: String) : IntExpression {

    override fun interpret(context: MutableMap<String, Int>): Int {
        return context[name] ?: error("Переменной  '$name' нет в системе")
    }
}

// объявление
class DeclarationExpression(
    private val variableNames: String,
) : UnitExpression {
    private val intDefault: Int = 0
    override fun interpret(context: MutableMap<String, Int>) {
        context.put(variableNames, intDefault)
    }
}


// присваивание
class AssignmentExpression(
    private val variableName: String,
    private val right: IntExpression
) : UnitExpression {
    override fun interpret(context: MutableMap<String, Int>) {
        context[variableName] = right.interpret(context)
    }
}



open class ArithmeticExpression(
    protected var left: IntExpression,
    protected var right: IntExpression
) : IntExpression {
    override fun interpret(context: MutableMap<String, Int>): Int {
     return 0//?????????
    }
}

class MinusExpression(left: IntExpression, right: IntExpression)
    : ArithmeticExpression(left, right), IntExpression {

    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) - right.interpret(context)
    }
}

class PlusExpression(left: IntExpression, right: IntExpression)
    : ArithmeticExpression(left, right), IntExpression {

    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) + right.interpret(context)
    }
}

class MultiplicationExpression(left: IntExpression, right: IntExpression)
    : ArithmeticExpression(left, right), IntExpression {

    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) * right.interpret(context)
    }
}

class DivisionExpression(left: IntExpression, right: IntExpression)
    : ArithmeticExpression(left, right), IntExpression {

    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) / right.interpret(context)
    }
}



enum class ComparisonOperator {
    EQ, UNEQ, LS, MR, LAE, MAE
}

class IfExpression(
    private val left: IntExpression,
    private val operator: ComparisonOperator,
    private val right: IntExpression,
    private val thenBlock: List<UnitExpression>
) : UnitExpression {
    override fun interpret(context: MutableMap<String, Int>) {
        val l = left.interpret(context)
        val r = right.interpret(context)
        val condition = when (operator) {
            ComparisonOperator.EQ -> l == r
            ComparisonOperator.UNEQ -> l != r
            ComparisonOperator.LS -> l < r
            ComparisonOperator.MR -> l > r
            ComparisonOperator.LAE -> l <= r
            ComparisonOperator.MAE -> l >= r
        }
        if (condition) {
            for (unitExpression in thenBlock){
                unitExpression.interpret(context)
            }
        }
    }
    class OutputExpression(
        private val expression: IntExpression
    ) : UnitExpression {
        override fun interpret(context: MutableMap<String, Int>) {
            val result = expression.interpret(context)
            OutputLogger.log(result.toString())
        }
    }
}

class WhileExpression(
    private val left: IntExpression,
    private val operator: ComparisonOperator,
    private val right: IntExpression,
    private val body: List<UnitExpression>
) : UnitExpression {
    override fun interpret(context: MutableMap<String, Int>) {
        while (
            when (operator) {
                ComparisonOperator.EQ -> left.interpret(context) == right.interpret(context)
                ComparisonOperator.UNEQ -> left.interpret(context) != right.interpret(context)
                ComparisonOperator.LS -> left.interpret(context) < right.interpret(context)
                ComparisonOperator.MR -> left.interpret(context) > right.interpret(context)
                ComparisonOperator.LAE -> left.interpret(context) <= right.interpret(context)
                ComparisonOperator.MAE -> left.interpret(context) >= right.interpret(context)
            }
        ) {
            for (cmd in body) {
                cmd.interpret(context)
            }
        }
    }
}

class ArrayDeclarationExpression(
    private val name: String,
    private val size: Int
) : UnitExpression {
    override fun interpret(context: MutableMap<String, Int>) {
        ArrayMemory.arrays[name] = IntArray(size) {0}
    }
}

object ArrayMemory {
    val arrays: MutableMap<String, IntArray> = mutableMapOf()
}

class ArrayAssignmentExpression(
    private val name: String,
    private val index: IntExpression,
    private val value: IntExpression
) : UnitExpression {
    override fun interpret(context: MutableMap<String, Int>) {
        val arr = ArrayMemory.arrays[name] ?: error("этого массива нету ")
        val idx = index.interpret(context)
        val v = value.interpret(context)
        if (idx in arr.indices) {
            arr[idx] = v
        } else error("индекс вне массива $name")
    }
}

class ArrayReadExpression(
    private val name: String,
    private val index: IntExpression
) : IntExpression {
    override fun interpret(context: MutableMap<String, Int>): Int {
        val arr = ArrayMemory.arrays[name] ?: error("этого массива нету ")
        val idx = index.interpret(context)
        if (idx in arr.indices) {
            return arr[idx]
        } else error("индекс вне массива $name")
    }
}

fun main() {

//    var q = 1 + (9 - 5);
//    var context: MutableMap<String, Int> = mutableMapOf()
//    context = mutableMapOf("a" to 10, "b" to 5);
//    var expressionFull = DeclarationExpression(
//        "Sosiska", PlusExpression(
//            NumberExpression(context.getOrDefault("a", 0)),
//            MinusExpression(NumberExpression(344), NumberExpression(432))
//        )
//    )
//    var result = expressionFull.interpret(context);
//
//
//
////    if (result != Unit) {
////        println("Result: $result")
////    }else{
////        for (varible in context){
////
////        }
////    }
//    println(context)
}