package com.example.codeblockapp

class BuisinessLogic(
    val money: Int = Int.MAX_VALUE
) {
    public fun Report(): Unit {
        println(money)
    }
}