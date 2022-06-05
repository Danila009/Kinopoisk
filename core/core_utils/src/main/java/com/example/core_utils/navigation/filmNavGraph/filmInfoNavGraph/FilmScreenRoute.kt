package com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph

import com.example.core_utils.state.VideoPlayerState

sealed class FilmScreenRoute(val route:String){
    object FilmInfo: FilmScreenRoute("film_info_screen?filmId={filmId}"){
        fun base(
            filmId:Int
        ):String = "film_info_screen?filmId=$filmId"
    }
    object SerialInfoSeason: FilmScreenRoute("serial_info_season?filmId={filmId}" +
            "&characters={characters}"){
        fun base(
            filmId:String,
            characters:Boolean = false
        ):String = "serial_info_season?filmId=$filmId&characters=$characters"
    }
    object WebScreen: FilmScreenRoute("web_screen?webUrl={webUrl}"
    ){
        fun base(
            webUrl:String,
        ):String = "web_screen?webUrl=$webUrl"
    }
    object VideoPlayer: FilmScreenRoute("video_player?videoState={videoState}" +
            "&youtubeUrl={youtubeUrl}&youtubeTitle={youtubeTitle}"){
        fun base(
            videoState: VideoPlayerState,
            youtubeUrl:String? = null,
            youtubeTitle:String? = null
        ):String = "video_player?videoState=${videoState.name}" +
                "&youtubeUrl=$youtubeUrl&youtubeTitle=$youtubeTitle"
    }
}
