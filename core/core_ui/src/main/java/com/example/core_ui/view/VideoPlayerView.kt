package com.example.core_ui.view

import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.example.core_ui.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar
import java.net.URL


@Composable
fun VideoPlayerView(
    url: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
//    var playWhenReady by remember { mutableStateOf(true) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = true
            prepare()
            pause()
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Composable
fun YouTubeVideoPlayerUrl(
    url: String,
    title: String,
    modifier: Modifier,
    play:Boolean = false,
    showFullscreenButton:Boolean = true,
    onPaused:(Boolean) -> Unit = {}
){
    val videoId = url.parseUrlYouTube()

    YouTubeVideoPlayer(
        videoId, title, modifier, play, showFullscreenButton,onPaused
    )
}

@Composable
fun YouTubeVideoPlayerId(
    videoId: String,
    title: String,
    modifier: Modifier,
    play:Boolean = false,
    showFullscreenButton:Boolean = true,
    onPaused:(Boolean) -> Unit = {}
){
    YouTubeVideoPlayer(
        videoId, title, modifier, play, showFullscreenButton, onPaused
    )
}

@Composable
private fun YouTubeVideoPlayer(
    videoId:String,
    title: String,
    modifier:Modifier,
    play: Boolean = false,
    showFullscreenButton:Boolean = true,
    onPaused:(Boolean) -> Unit
){
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val youtubePlayerView = remember {
        YouTubePlayerView(context).apply {
            this.id = R.id.third_party_player_view
            enableAutomaticInitialization = false
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    val youTubePlayerSeekBar = remember {
        YouTubePlayerSeekBar(context).apply {
            id = R.id.youtube_player_seekbar
        }
    }

    val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            val defaultPlayerUiController =
                DefaultPlayerUiController(youtubePlayerView, youTubePlayer)

            defaultPlayerUiController.apply {
                setVideoTitle(title)
                showCurrentTime(true)
                showSeekBar(true)
                showFullscreenButton(showFullscreenButton)
            }

            youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

            youTubePlayer.loadOrCueVideo(
                lifecycle = lifecycle,
                videoId = videoId,
                startSeconds = 0f
            )

            if (play){
                youTubePlayer.play()
            }else {
                youTubePlayer.pause()
            }
        }

        override fun onStateChange(
            youTubePlayer: YouTubePlayer,
            state: PlayerConstants.PlayerState
        ) {
            onPaused(state != PlayerConstants.PlayerState.PLAYING)
        }
    }

    val options: IFramePlayerOptions = IFramePlayerOptions.Builder()
        .controls(0)
        .build()

    try {
        youtubePlayerView.initialize(listener, options)
    }catch (e:Exception){
        Log.e("YoutubePlayerView", e.message.toString())
    }

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                youtubePlayerView
            }, update = {

            }
        )
    ){
        onDispose {
            youtubePlayerView.release()
        }
    }
}

private fun String.parseUrlYouTube():String{
    val url = URL(this)
    return try {
        url.query.removeRange(IntRange(0,1))
    }catch (e:Exception) {
        this.removeRange(IntRange(0,16))
    }
}