package com.example.codeblockapp

import android.graphics.drawable.GradientDrawable.Orientation
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = false, showBackground = true)
@Composable
fun CodeScreen() {
    var color = remember{
        mutableStateOf(Color(0xFF4CAF50))
    }
    var isRunning = remember{
        mutableStateOf(false)
    }
    var text = remember{
        mutableStateOf("▶")
    }
    var blocks = listOf(Block(), Block(), Block(), Block())

    Column {
        Box (
            modifier = Modifier.fillMaxSize().weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button (
                onClick = {
                    if (isRunning.value) {
                        color.value = Color(0xFF4CAF50)
                        text.value = "▶"
                        isRunning.value = false
                    } else {
                        color.value = Color(0xFFE52929)
                        text.value = "■"
                        isRunning.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0x00FFFFFF),
                    containerColor = color.value),
                shape = CircleShape,
                modifier = Modifier.padding(20.dp).size(50.dp).align(Alignment.TopEnd)
            ) {
                Text(text.value, color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center)
            }
            Text("Полe для блоков", fontSize = 16.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFFFE2D1)),

            contentAlignment = Alignment.Center
        ) {
//            LazyRow(modifier = Modifier
//                .fillMaxSize(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                items(blocks) { block ->
//                    block.Create()
//                }
//            }
        }
    }
}