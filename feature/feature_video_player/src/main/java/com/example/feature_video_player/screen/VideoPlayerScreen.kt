package com.example.feature_video_player.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.view.YouTubeVideoPlayerUrl
import com.example.core_utils.state.VideoPlayerState


@Composable
fun VideoPlayerScreen(
    videoPlayerState: VideoPlayerState,
    youtubeUrl:String?,
    youtubeTitle:String?
){
    val screenWithDb = LocalConfiguration.current.screenWidthDp.dp
    val screenHeightDb = LocalConfiguration.current.screenHeightDp.dp

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = primaryBackground
    ) {
        when(videoPlayerState) {
            VideoPlayerState.YOUTUBE -> {
                if (youtubeUrl != null && youtubeTitle != null) {
                    YouTubeVideoPlayerUrl(
                        url = youtubeUrl,
                        title = youtubeTitle,
                        play = true,
                        showFullscreenButton = false,
                        modifier = Modifier.size(screenWithDb,screenHeightDb)
                    )
                }
            }
        }
    }
}