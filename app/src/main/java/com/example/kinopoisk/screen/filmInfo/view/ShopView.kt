package com.example.kinopoisk.screen.filmInfo.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun ShopView(
    shop: Shop
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 5.dp
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = secondaryBackground
        ),
        shape = AbsoluteRoundedCornerShape(15.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Купить фильм ${shop.price} P")
    }
}