package com.example.kinopoisk.screen.login.view

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun PasswordTextFieldView(
    label:String,
    value:MutableState<String>
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
        ), visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go
        ), modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun EmailTextFieldView(
    label:String,
    value:MutableState<String>
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
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Go
        ), modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun BaseTextFieldView(
    label:String,
    value:MutableState<String>
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
            imeAction = ImeAction.Go
        ), modifier = Modifier.padding(5.dp)
    )
}