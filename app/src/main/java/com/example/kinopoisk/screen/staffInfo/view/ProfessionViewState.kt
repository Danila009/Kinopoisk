package com.example.kinopoisk.screen.staffInfo.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.staff.FilmStaff
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.staffInfo.viewState.ProfessionKeyViewState
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun ProfessionViewState(
   professionKeyViewState: ProfessionKeyViewState,
   navController: NavController,
   item:FilmStaff
) {
    if (item.professionKey == professionKeyViewState.name){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp, vertical = 5.dp)
                .clickable {
                    navController.navigate(
                        Screen.FilmInfo.base(
                            item.filmId.toString()
                        )
                    )
                },
            shape = AbsoluteRoundedCornerShape(15.dp),
            backgroundColor = primaryBackground,
            elevation = 8.dp
        ) {
            Column {
                Text(
                    text = item.nameRu.toString(),
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
                item.rating?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(start = 5.dp),
                        color = Converters().rating(it.toFloat())
                    )
                }
                Text(
                    text = item.description.toString(),
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
            }
        }
    }
}