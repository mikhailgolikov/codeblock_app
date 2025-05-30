package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeScreen(
    algorithmBlocks: SnapshotStateList<Block>
) {
    val color = remember { mutableStateOf(Color(0xFF4CAF50)) }
    val isRunning = remember { mutableStateOf(false) }
    val text = remember { mutableStateOf("▶") }

    val availableBlocks = listOf(
//        DeclarationBlock(),
//        AssignmentBlock(),
        PlusBlock(),
    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(algorithmBlocks) { block ->
                    block.Create() {}
                }
            }
            Button(
                onClick = {
                    if (isRunning.value) {
                        color.value = Color(0xFF4CAF50)
                        text.value = "▶"
                        isRunning.value = false


                    }else {
                        color.value = Color(0xFFE52929)
                        text.value = "■"
                        isRunning.value = true

                        // погнал интерпретатор
//                        val context = mutableMapOf<String, Int>()
//                        OutputLogger.clear()
//                        for (block in algorithmBlocks) {
//                            val expr = block.toUnitExpression()
//                            try {
//                                expr?.interpret(context)
//                            } catch (e: Exception) {
//                                println("ошибка ${block.text}: ${e.message}")
//                            }
//                        }
//
//                        println("Все переменные: $context")
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color.value
                ),
                shape = CircleShape,
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(text.value, color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center)
            }

        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFFFE2D1)),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(availableBlocks) { block ->
                    val newBlock = block.Copy()
                    newBlock.clickable = false
//                    block.inputBlock = newBlock

                    if (block.inputBlock == null) {
                        block.Create {
                            algorithmBlocks.add(newBlock)
                        }
                    }
                    else {
                        block.Create {

                        }
                    }
                }
            }
        }
    }
}