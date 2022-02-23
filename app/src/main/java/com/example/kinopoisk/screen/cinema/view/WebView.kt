package com.example.kinopoisk.screen.cinema.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.R
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.key.WebScreenKey
import com.example.kinopoisk.utils.Converters

@Composable
fun WebView(
   navController: NavController,
   cinema: Cinema
) {
    LazyRow(content = {
        items(4){ item ->
            when(item){
                0->{
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.WebScreen.base(
                                keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                webUrl = cinema.website,
                                cinemaId = cinema.id.toString()
                            )
                        )
                    }, modifier = Modifier
                        .padding(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.web),
                            contentDescription = null
                        )
                    }
                }
                1->{
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.WebScreen.base(
                                keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                webUrl = cinema.webVk,
                                cinemaId = cinema.id.toString()
                            )
                        )
                    }, modifier = Modifier
                        .padding(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.vk),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
                2->{
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.WebScreen.base(
                                keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                                webUrl = cinema.webInstagram,
                                cinemaId = cinema.id.toString()
                            )
                        )
                    }, modifier = Modifier
                        .padding(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.instagram),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
                3->{
                    IconButton(onClick = {
                    navController.navigate(
                        Screen.WebScreen.base(
                            keyString = Converters().encodeToString(WebScreenKey.CINEMA),
                            webUrl = cinema.webFacebook,
                            cinemaId = cinema.id.toString()
                        )
                    )
                }, modifier = Modifier
                    .padding(5.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
                }
            }
        }
    })
}