package com.example.feature_film_info.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.feature_film_info.viewModel.FilmInfoViewModel

@Composable
internal fun ShopView(
    filmInfoViewModel: FilmInfoViewModel,
//    shop: Shop,
    checkPurchase:Boolean
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
        onClick = {
            if (!checkPurchase){
//                filmInfoViewModel.postPurchase(
//                    Purchase(
//                        date = Converters().getCurrentTime(),
//                        shop = shop
//                    )
//                )
            }
        }
    ) {
//        Text(text = if (checkPurchase) "Купить фильм ${shop.price} P" else "Филь куплин")
    }
}