package com.example.core_network_data.common

object ConstantsUrl {

    const val FILM_URL = "/api/v2.2/films"
    const val PREMIERE_URL = "/api/v2.2/films/premieres"
    const val RELEASE_URL = "/api/v2.1/films/releases"
    const val FILTER_URL = "/api/v2.2/films/filters"
    const val FILM_INFO_ID_URL = "/api/v2.2/films/{id}"
    const val SEASONS_ID_URL = "/api/v2.2/films/{id}/seasons"

    const val SHOP_URL = "/kinopoisk/Shop"

    const val CINEMA_URL = "/kinopoisk/Cinema"

    const val PLAYLIST_URL = "/kinopoisk/Playlist"

    const val SEARCH_PERSON_URL = "/api/v1/persons"

    const val HISTORY_MOVIE_URL = "/kinopoisk/User/History/Movie"
    const val HISTORY_SEARCH_URL = "/kinopoisk/User/History/Search"

    const val USER_FAVORITE_MOVIE_URL = "/kinopoisk/User/Content/Favorite/Movie"
    const val USER_WATCH_LATER_URL = "/kinopoisk/User/Content/WatchLater/Movie"
    const val USER_FAVORITE_STAFF_URL = "/kinopoisk/User/Content/Favorite/Staff"

    const val AUTHORIZATION_URL = "/kinopoisk/Authorization"
    const val REGISTRATION_URL = "/kinopoisk/Registration"
    const val USER_INFO_URL = "/kinopoisk/User/Info"
    const val UPDATE_PASSWORD_URL = "/kinopoisk/User/Update/Password"

    const val RIAK_AND_MORTY_CHARACTER_URL = "/api/character"
    const val RIAK_AND_MORTY_LOCATION_URL = "/api/location"
    const val RIAK_AND_MORTY_EPISODES_URL = "/api/episode"
}