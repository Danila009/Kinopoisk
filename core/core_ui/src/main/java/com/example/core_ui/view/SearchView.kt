package com.example.core_ui.view

import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
fun SearchView(
    search:MutableState<String>,
    close:MutableState<Boolean> = mutableStateOf(false)
) {
    TextField(
        value = search.value,
        onValueChange = {
            search.value = it
        }, placeholder = {
            Text(text = "Search")
        }, leadingIcon = {
            if (close.value){
                IconButton(onClick = {
                    search.value = ""
                    close.value = false
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = secondaryBackground
                    )
                }
            }else{
                IconButton(onClick = {
                    close.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = secondaryBackground
                    )
                }
            }
        }, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ), shape = AbsoluteRoundedCornerShape(5.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = secondaryBackground,
            backgroundColor = primaryBackground
        ), trailingIcon = {
            if (search.value.isNotEmpty()){
                IconButton(onClick = { search.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = secondaryBackground
                    )
                }
            }
        }
    )
}