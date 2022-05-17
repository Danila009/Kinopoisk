package com.example.feature_profile.view.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Composable
internal fun PlaylistAdmin(
    playlistAdmin:Response<Playlist>,
    navController:NavController
) {
    if (playlistAdmin !is Response.Error){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Playlist admin:",
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
            if (playlistAdmin is Response.Success){
                items(playlistAdmin.data!!.items){ item ->
                    Card(
                        shape = AbsoluteRoundedCornerShape(15.dp),
                        modifier = Modifier
                            .size(150.dp)
                            .padding(5.dp)
                            .clickable {
                                navController.navigate(
                                    PlaylistScreenRoute.AdminListFilmItem.base(
                                    adminFilmListItemId = item.id.toString()
                                ))
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = item.title)
                        }
                    }
                }
            }else{
                items(3){
                    ImageShimmer(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(150.dp)
                    )
                }
            }
        })
    }
}