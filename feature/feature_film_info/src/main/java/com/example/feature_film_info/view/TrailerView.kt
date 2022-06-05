package com.example.feature_film_info.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.trailer.Trailer
import com.example.core_network_domain.model.movie.trailer.TrailerSite
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.YouTubeVideoPlayerUrl
import com.example.core_ui.view.VideoPlayerView
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.state.VideoPlayerState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun TrailerView(
    navController: NavController,
    trailer:Response<Trailer>
) {
    if (trailer is Response.Success){
        val screenWithDb = LocalConfiguration.current.screenWidthDp.dp

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Trailers:",
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

        trailer.data?.items?.size?.let { size ->
            HorizontalPager(count = size) { page ->
                repeat(size){ index ->
                    if (page == index){
                        if(trailer.data?.items!![index].site == TrailerSite.YOUTUBE){
                            YouTubeVideoPlayerUrl(
                                url = trailer.data?.items!![index].url,
                                title = trailer.data?.items!![index].name,
                                play = false,
                                modifier = Modifier
                                    .size(screenWithDb, 200.dp)
                                    .padding(10.dp),
                                onPaused = {
                                    if(!it){
                                        navController.navigate(
                                            FilmScreenRoute.VideoPlayer.base(
                                                videoState = VideoPlayerState.YOUTUBE,
                                                youtubeUrl = trailer.data?.items!![index].url,
                                                youtubeTitle = trailer.data?.items!![index].name
                                            )
                                        )
                                    }
                                }
                            )
                        }else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                VideoPlayerView(
                                    url = trailer.data?.items!![index].url,
                                    modifier = Modifier
                                        .size(screenWithDb, 200.dp)
                                        .padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}