package com.example.feature_cinema_info.view

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
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.R

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
                            FilmScreenRoute.WebScreen.base(
                                webUrl = cinema.website
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
                            FilmScreenRoute.WebScreen.base(
                                webUrl = cinema.webVk
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
                            FilmScreenRoute.WebScreen.base(
                                webUrl = cinema.webInstagram
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
                        FilmScreenRoute.WebScreen.base(
                            webUrl = cinema.webFacebook
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