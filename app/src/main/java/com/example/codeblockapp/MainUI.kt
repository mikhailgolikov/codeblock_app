package com.example.codeblockapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainUI() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Run", "Code")

    val items = listOf("Кнопка start","Переменные","Операции","Условия","Циклы")
    val selectedItem = remember{ mutableStateOf(items[0])}
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                items.forEach{item->
                    TextButton(onClick =  {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.LightGray, containerColor = Color.Transparent)){
                        Text(item, fontSize =  22.sp)
                    }
                }
            }
        },
        scrimColor = Color.DarkGray,
        content = {
            Column {
                Row(
                    modifier = Modifier
                        .background(color = Color(0xFFeb6acf))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Меню", tint = Color.White)
                    }


                    TabRow(
                        selectedTabIndex = selectedTab,
                        containerColor = Color(0xFFeb6acf),
                        contentColor = Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTab == index,
                                onClick = { selectedTab = index },
                                text = { Text(title.uppercase(), fontSize = 14.sp) }
                            )
                        }
                    }
                }

                when (selectedTab) {
                    0 -> RunScreen()
                    1 -> CodeScreen()
                }
            }
        }
    )
}
