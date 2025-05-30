package com.example.codeblockapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class AssignmentBlock (
    var name: String = "",
    var value: Block? = null,
) : Block(color = Color.Green), UnitBlock {

    override fun toUnitExpression(): UnitExpression? {
        val intValue = (value as? IntBlock)?.toIntExpression() ?: return null
        return AssignmentExpression(name, intValue)
    }

    @Composable
    override fun Create(onClick: () -> Unit) {
        super.Create(onClick)

        var stateName by remember { mutableStateOf(name) }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                TextField(
                    value = stateName,
                    enabled = enabled,
                    onValueChange = {
                        stateName = it
                        name = it
                    },
                    singleLine = true,
                    modifier = Modifier.width(80.dp)
                )

                Text(text = "=", fontSize = 20.sp, color = Color.Black,
                    modifier = Modifier.padding(8.dp))

                Box(
                    modifier = Modifier.width(80.dp)
                )
            }
        }
    }

    override fun Copy() : AssignmentBlock {
        return AssignmentBlock(name = name, value = value)
    }
}