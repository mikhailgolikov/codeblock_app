package com.example.codeblockapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class DeclarationBlock(var name: String = "")
    : Block(color = Color.Red), UnitBlock {

    override fun toUnitExpression(): UnitExpression? {
        return DeclarationExpression(name)
    }

    @Composable
    override fun Create(
        onClick: ()->Unit
    ) {
        super.Create(onClick)

        var stateName by remember { mutableStateOf(name) }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 30.dp, vertical = 15.dp)
                    .width(150.dp),
                value = stateName,
                enabled = enabled,
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                ),
                onValueChange = { newText ->
                    if (enabled) {
                        stateName = newText
                        name = newText
                    }
                }
            )
        }
    }

    override fun Copy() : DeclarationBlock {
        return DeclarationBlock(name = name)
    }
}