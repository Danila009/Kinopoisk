package com.example.feature_home.view

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
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
internal fun CinemaView(
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
        if (cinema.isNotEmpty()){
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
                            cameraPositionState = rememberCameraPositionState{
                                position = CameraPosition.fromLatLngZoom(
                                    LatLng(
                                        item.latitude,
                                        item.longitude
                                    ),
                                    13f
                                )
                            },
                            uiSettings = MapUiSettings(
                                compassEnabled = false,
                                indoorLevelPickerEnabled = false,
                                mapToolbarEnabled = false,
                                myLocationButtonEnabled = false,
                                rotationGesturesEnabled = false,
                                scrollGesturesEnabled = false,
                                scrollGesturesEnabledDuringRotateOrZoom = false,
                                tiltGesturesEnabled = false,
                                zoomControlsEnabled = false,
                                zoomGesturesEnabled = false
                            ),
                            content = {
                                Marker(position = LatLng(
                                    item.latitude,
                                    item.longitude
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
        }else{
            items(10){
                BaseRawListShimmer(
                    imageWidth = 300.dp,
                    imageHeight = 200.dp
                )
            }
        }
    })
}