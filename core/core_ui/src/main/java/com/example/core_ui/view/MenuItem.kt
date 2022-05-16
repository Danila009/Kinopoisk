package com.example.core_ui.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.R

@Composable
fun MenuItem(
    list:List<String>,
    value:String,
    label:String? = null,
    onSelected:(String) -> Unit,
) {
    var expandedMenu by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedMenu) 90f else 0f
    )

    var valuesState by remember { mutableStateOf(value) }

    Column {
        Box(
            modifier = Modifier
                .background(primaryBackground)
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .clickable {
                        expandedMenu = true
                    }
                    .padding(5.dp)
                    .background(primaryBackground),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    text = label ?: ""
                )

                Text(
                    text = valuesState
                )

                Icon(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(18.dp)
                        .align(Alignment.CenterVertically)
                        .rotate(rotationState),
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                    contentDescription = "Arrow",
                    tint = secondaryBackground
                )
            }

            Divider(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.BottomStart),
                thickness = 0.5.dp
            )
        }

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false }
        ) {
            list.forEach { item ->
                DropdownMenuItem(onClick = {
                    valuesState = item
                    onSelected(item)
                    expandedMenu = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}