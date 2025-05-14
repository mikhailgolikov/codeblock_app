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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun RunScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize()
//            .padding(12.dp),
        ){

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
//                .shadow(4.dp, RoundedCornerShape(12.dp))
//                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ){
            Text("Основное поле для вывода результата", fontSize = 16.sp)
        }

    }
}


