package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CostumesScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color(0XFFFFF8E1), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ){
        Text("Инструменты для создания спрайтов",color = Color.Black, fontSize = 16.sp)

    }
}
