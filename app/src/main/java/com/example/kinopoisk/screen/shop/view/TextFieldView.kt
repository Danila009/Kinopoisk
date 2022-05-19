package com.example.kinopoisk.screen.shop.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground

@Composable
fun NumberTextField(
    label:String,
    value: MutableState<String>
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = {
            Text(text = label)
        }, shape = AbsoluteRoundedCornerShape(5.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = secondaryBackground,
            backgroundColor = primaryBackground,
            cursorColor = secondaryBackground,
            focusedLabelColor = secondaryBackground
        ), keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Number
        ), modifier = Modifier.padding(5.dp)
    )
}