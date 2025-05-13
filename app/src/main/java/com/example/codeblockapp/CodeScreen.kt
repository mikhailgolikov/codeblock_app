package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CodeScreen(){
    var color = remember{
        mutableStateOf(Color(0xFF4CAF50))
    }
    var isRunning = remember{
        mutableStateOf(false)
    }
    var text = remember{
        mutableStateOf("▶")
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(20.dp)
                        .background(color = color.value, shape = CircleShape)
                        .size(48.dp)
                        .clickable {
                            if (isRunning.value) {
                                color.value = Color(0xFF4CAF50)
                                text.value = "▶"
                                isRunning.value = false
                            } else {
                                color.value = Color(0xFFF44336)
                                text.value = "⏹"
                                isRunning.value = true
                            }



                        },

                    contentAlignment = Alignment.Center,


                    ) {
                    Text(text.value, color = Color.White, fontSize = 20.sp)

                }
            Text("Полe для блоков", fontSize = 16.sp)
        }
    }

}
