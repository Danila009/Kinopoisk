package com.example.feature_home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_home.R
import com.example.core_database_domain.common.UserRole
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.filmNavGraph.playlistNavGraph.PlaylistScreenRoute
import com.example.core_utils.state.NameTopState
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Composable
internal fun PlaylistView(
    navController: NavController,
    userRole:UserRole,
    playlist:List<Playlist>
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
                                    PlaylistScreenRoute.Playlist.base(
                                        NameTopState.TOP_100_POPULAR_FILMS.name
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
                                    PlaylistScreenRoute.Playlist.base(
                                        NameTopState.TOP_250_BEST_FILMS.name
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
                                    PlaylistScreenRoute.Playlist.base(
                                        NameTopState.TOP_AWAIT_FILMS.name
                                    )
                                )
                            }
                    )
                }
            }
        }
        item {
            if (userRole == UserRole.Admin){
                Card(
                    shape = AbsoluteRoundedCornerShape(15.dp),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(5.dp)
                        .clickable {
                            navController.navigate(PlaylistScreenRoute.FilmListAdd.base())
                        }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(75.dp),
                            tint = Color.Red
                        )
                    }
                }
            }
        }

        items(playlist){ item ->
            Card(
                shape = AbsoluteRoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(150.dp)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(PlaylistScreenRoute.AdminListFilmItem.base(
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
    })
}