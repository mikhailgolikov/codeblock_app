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
    private val right: IntExpression
) : UnitExpression {
    override fun interpret(context: MutableMap<String, Int>) {
        context.put(variableNames, right.interpret(context))
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

class MinusExpression(
    private var left: IntExpression,
    private var right: IntExpression
) : IntExpression {
    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) - right.interpret(context)

    }

}

class PlusExpression(
    private var left: IntExpression,
    private var right: IntExpression
) : IntExpression {


    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) + right.interpret(context)

    }

}

class MultiplicationExpression(
    private var left: IntExpression,
    private var right: IntExpression
) : IntExpression {


    override fun interpret(context: MutableMap<String, Int>): Int {
        return left.interpret(context) * right.interpret(context)

    }

}

class DivisionExpression(
    private var left: IntExpression,
    private var right: IntExpression
) : IntExpression {

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