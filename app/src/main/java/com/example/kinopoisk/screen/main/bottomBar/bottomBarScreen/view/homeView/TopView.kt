package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.homeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.R
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmTop.viewState.NameTopViewState
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun TopView(
    navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Топы:",
            modifier = Modifier.padding(5.dp),
            color = secondaryBackground,
            fontWeight = FontWeight.Bold
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
        items(4) {
            when(it){
                1->{
                    Image(
                        painter = painterResource(id = R.drawable.iamgetopc),
                        contentDescription = null,
                        Modifier
                            .size(150.dp)
                            .padding(5.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.FilmTop.base(
                                        Converters().encodeToString(NameTopViewState.TOP_100_POPULAR_FILMS)
                                    )
                                )
                            }
                    )
                }
                2->{
                    Image(
                        painter = painterResource(id = R.drawable.toplmagea),
                        contentDescription = null,
                        Modifier
                            .size(150.dp)
                            .padding(5.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.FilmTop.base(
                                        Converters().encodeToString(NameTopViewState.TOP_250_BEST_FILMS)
                                    )
                                )
                            }
                    )
                }
                3->{
                    Image(
                        painter = painterResource(id = R.drawable.orig),
                        contentDescription = null,
                        Modifier
                            .size(150.dp)
                            .padding(5.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.FilmTop.base(
                                        Converters().encodeToString(NameTopViewState.TOP_AWAIT_FILMS)
                                    )
                                )
                            }
                    )
                }
            }
        }
    })
}