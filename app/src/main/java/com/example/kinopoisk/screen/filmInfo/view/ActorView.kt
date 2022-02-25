package com.example.kinopoisk.screen.filmInfo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.navigation.navGraph.staffInfoNavGraph.constants.StaffInfoScreenRoute
import com.example.kinopoisk.screen.main.key.StaffInfoScreenKey
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun StaffView(
    staff:MutableState<List<Staff>>,
    navController: NavController,
    filmId:String
) {
    if (staff.value.isNotEmpty()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Актёры",
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
            items(staff.value){ item ->
                if (item.professionKey == "ACTOR"){
                    Card(
                        shape = AbsoluteRoundedCornerShape(7.dp),
                        modifier = Modifier.padding(5.dp)
                            .clickable {
                                navController.navigate(StaffInfoScreenRoute.StaffInfo.base(
                                    staffId = item.staffId.toString(),
                                    filmId = filmId,
                                    key = Converters().encodeToString(StaffInfoScreenKey.FILM)
                                ))
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = item.posterUrl,
                                    builder = {
                                        crossfade(true)
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .height(80.dp)
                                    .width(50.dp)
                            )
                            Column {
                                Text(
                                    text = item.nameRu,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        top = 3.dp,
                                        bottom = 2.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Съёмочная группа:",
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
            items(staff.value){ item ->
                if (
                    item.professionKey == "DIRECTOR"
                    || item.professionKey == "EDITOR"
                    || item.professionKey == "DESIGN"
                    || item.professionKey == "COMPOSER"
                    || item.professionKey == "OPERATOR"
                    || item.professionKey == "WRITER"
                    || item.professionKey == "VOICE_DIRECTOR"
                    || item.professionKey == "PRODUCER"
                ){
                    Card(
                        shape = AbsoluteRoundedCornerShape(7.dp),
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                navController.navigate(StaffInfoScreenRoute.StaffInfo.base(
                                    staffId = item.staffId.toString(),
                                    filmId = filmId,
                                    key = Converters().encodeToString(StaffInfoScreenKey.FILM)
                                ))
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = item.posterUrl,
                                    builder = {
                                        crossfade(true)
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .height(80.dp)
                                    .width(50.dp)
                            )
                            Column {
                                Text(
                                    text = item.nameRu,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        top = 3.dp,
                                        bottom = 2.dp
                                    )
                                )
                                Text(
                                    text = item.professionText,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        top = 2.dp,
                                        bottom = 3.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        })
    }
}