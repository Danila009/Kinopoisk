package com.example.feature_cinema_info.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core_network_domain.model.cinema.Phone

@Composable
fun PhoneView(
    phoneItem: Phone,
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