package com.example.codeblockapp

import androidx.compose.runtime.mutableStateListOf

object OutputLogger {
    val log = mutableStateListOf<String>()

    fun log(message: String) {
        log.add(message)
    }

    fun clear() {
        log.clear()
    }

    fun getAll(): List<String> = log
}
