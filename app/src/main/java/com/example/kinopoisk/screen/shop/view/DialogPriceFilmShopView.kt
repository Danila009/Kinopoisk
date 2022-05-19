package com.example.kinopoisk.screen.shop.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.shop.Shop
import com.example.core_utils.navigation.shopNavGraph.ShopScreenRoute
import com.example.kinopoisk.screen.shop.shopViewModel.ShopViewModel

@Composable
fun DialogPriceFilmShopView(
    navController: NavController,
    shopViewModel: ShopViewModel,
    checkDialog: MutableState<Boolean>,
    filmItem: FilmItem
) {
    val context = LocalContext.current
    val price = remember { mutableStateOf("") }
    if (checkDialog.value){
        AlertDialog(
            shape = AbsoluteRoundedCornerShape(20.dp),
            onDismissRequest = { checkDialog.value = false },
            title = {
                 Text(
                     text = "add film ${filmItem.nameRu} in shop",
                     modifier = Modifier
                         .padding(5.dp)
                         .fillMaxWidth(),
                     textAlign = TextAlign.Center
                 )
            }, text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NumberTextField(
                        label = "Price film",
                        value = price
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = secondaryBackground
                        ), modifier = Modifier.padding(5.dp),
                        shape = AbsoluteRoundedCornerShape(15.dp),
                        onClick = { checkDialog.value = false }) {
                        Text(text = "Close")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = secondaryBackground
                        ), modifier = Modifier.padding(5.dp),
                        shape = AbsoluteRoundedCornerShape(15.dp),
                        onClick = {
                            if (price.value.isNotEmpty()){
                                shopViewModel.postShopAddFilmItem(
                                    Shop(
                                        kinopoiskId = filmItem.kinopoiskId,
                                        nameRu = filmItem.nameRu!!,
                                        ratingKinopoisk = filmItem.ratingKinopoisk!!,
                                        posterUrlPreview = filmItem.posterUrlPreview!!,
                                        price = price.value.toInt()
                                    )
                                )
                                navController.navigate(ShopScreenRoute.Shop.route)
                            }else{
                                Toast.makeText(
                                    context,
                                    "is empty",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }) {
                        Text(text = "add film")
                    }
                }
            }
        )
    }
}