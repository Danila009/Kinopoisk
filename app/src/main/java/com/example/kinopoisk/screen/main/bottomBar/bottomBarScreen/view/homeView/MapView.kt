package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.homeView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.constants.CinemaScreenRoute
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@Composable
fun MapView(
    navController: NavController,
    cinema:List<Cinema>,
    checkNavMap:MutableState<Boolean>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Cinema:",
            modifier = Modifier.padding(5.dp),
            color = secondaryBackground,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = { navController.navigate(CinemaScreenRoute.CinemaMap.route) },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Все ->",
                color = secondaryBackground
            )
        }
    }
    LazyRow(content = {
        itemsIndexed(cinema){ index, item ->
            if (index < 5){
                if (checkNavMap.value){
                    LaunchedEffect(key1 = Unit, block ={
                        navController.navigate(
                            CinemaScreenRoute.CinemaInfo.base(
                                cinemaId = item.id
                            )
                        )
                    })
                }
                Column(
                    modifier = Modifier
                        .width(350.dp)
                        .height(250.dp)
                ) {
                    GoogleMap(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(300.dp)
                            .height(200.dp)
                            .clip(
                                AbsoluteRoundedCornerShape(15.dp)
                            ),
                        cameraPositionState = CameraPositionState(
                            CameraPosition(
                                LatLng(
                                    item.mapOne,
                                    item.mapTwo
                                ), 80f,1f,1f
                            )
                        ),
                        content = {
                            Marker(position = LatLng(
                                item.mapOne,
                                item.mapTwo
                            ), title = "${item.title} ${item.adress}",
                                onInfoWindowClick = {
                                    checkNavMap.value = true
                                })
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                checkNavMap.value = true
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.title,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Text(
                            text = item.rating.toString(),
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    })
}