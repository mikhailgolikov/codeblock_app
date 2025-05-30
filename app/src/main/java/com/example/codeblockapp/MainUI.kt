package com.example.codeblockapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun MainUI() {

    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Output", "Code")
    val algorithmBlocks = remember { mutableStateListOf<Block>() }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color(0xFFeb6acf),
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title.uppercase(), fontSize = 14.sp) })
            }
        }

        val algorithmBlocks = remember { mutableStateListOf<Block>() }

        when (selectedTab) {
            0 -> RunScreen()
            1 -> CodeScreen(algorithmBlocks)
        }
    }
}
