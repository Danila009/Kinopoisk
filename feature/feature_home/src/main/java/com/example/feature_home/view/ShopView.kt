package com.example.feature_home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.shop.Shop
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.shopNavGraph.ShopScreenRoute

@Composable
fun ShopView(
    navController: NavController,
    shop: Shop
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Shop:",
            modifier = Modifier.padding(5.dp),
            color = secondaryBackground,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = { navController.navigate(ShopScreenRoute.Shop.route)},
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Все ->",
                color = secondaryBackground
            )
        }
    }

    LazyRow(content = {
        items(shop.catecory){ item ->
            Column(
                modifier = Modifier.clickable {

                }
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = item.imageUrl,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(180.dp)
                        .width(140.dp)
                )
            }
        }
    })
}