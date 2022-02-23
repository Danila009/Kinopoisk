package com.example.kinopoisk.screen.cinema.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kinopoisk.api.model.cinema.PhoneItem

@Composable
fun PhoneView(
    phoneItem: PhoneItem,
    context:Context
) {
    TextButton(onClick = {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${phoneItem.phone}")
        context.startActivity(intent)
    }) {
        Text(
            text = phoneItem.phone,
            modifier = Modifier.padding(5.dp)
        )
    }
}