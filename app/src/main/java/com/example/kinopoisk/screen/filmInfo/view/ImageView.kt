package com.example.kinopoisk.screen.filmInfo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.filmInfo.ImageItem
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun ImageView(
    navController: NavController,
    image:LazyPagingItems<ImageItem>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Изображении:",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = secondaryBackground
        )

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Все ->",
                color = secondaryBackground
            )
        }
    }

    LazyRow(content = {
        items(image){ item ->
            Image(
                painter = rememberImagePainter(
                    data = item?.imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .height(150.dp)
                    .width(250.dp)
            )
        }
    })
}