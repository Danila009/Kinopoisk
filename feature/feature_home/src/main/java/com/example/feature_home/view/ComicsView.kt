package com.example.feature_home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.comicsNavGraph.ComicsScreenRoute
import com.example.core_utils.state.ComicsState

@Composable
fun ComicsView(
    comicsMarvel: ComicsMarvel,
    navController:NavController
) {
    if (comicsMarvel.data.results.isNotEmpty()){

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Comics:",
                modifier = Modifier.padding(5.dp),
                color = secondaryBackground,
                fontWeight = FontWeight.Bold
            )

            TextButton(
                onClick = { navController.navigate(ComicsScreenRoute.ComicsScreen.arguments(
                    comicsState = ComicsState.MARVEL.name
                )) },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Все ->",
                    color = secondaryBackground
                )
            }
        }

        LazyRow(content = {
            items(comicsMarvel.data.results){ item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "${item.thumbnail?.path}.${item.thumbnail?.extension}",
                            builder = {
                                crossfade(true)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(
                                width = 204.dp,
                                height = 133.dp
                            )
                    )

                    Text(
                        text = item.title.toString(),
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        })
    }
}