package com.example.core_network_data.common

object ConstantsUrl {

    const val FILM_URL = "/api/v2.2/films"
    const val PREMIERE_URL = "/api/v2.2/films/premieres"
    const val RELEASE_URL = "/api/v2.1/films/releases"
    const val FILTER_URL = "/api/v2.2/films/filters"
    const val FILM_INFO_ID_URL = "/api/v2.2/films/{id}"
    const val SEASONS_ID_URL = "/api/v2.2/films/{id}/seasons"
    const val BUDGET_FILM_ID_URL = "/api/v2.2/films/{id}/box_office"
    const val FACT_FILM_ID_URL = "/api/v2.2/films/{id}/facts"
    const val STAFF_URL = "/api/v1/staff"
    const val SIMILAR_ID_URL = "/api/v2.2/films/{id}/similars"
    const val SEQUEL_AND_PREQUEL_ID_URL = "/api/v2.1/films/{id}/sequels_and_prequels"
    const val FILM_DISTRIBUTION_ID_URL = "/api/v2.2/films/{id}/distributions"
    const val IMAGE_ID_URL = "/api/v2.2/films/{id}/images"
    const val REVIEW_URL = "/api/v1/reviews"
    const val TRAILER_URL = "/api/v2.2/films/{id}/videos"
    const val MOVIE_AWARDS_URL = "/api/v2.2/films/{id}/awards"

    const val SHOP_URL = "/kinopoisk/Shop"

    const val CINEMA_URL = "/kinopoisk/Cinema"
    const val CINEMA_PHOTO_URL = "/kinopoisk/Cinema/{id}/Photo"
    const val CINEMA_PHONE_URL = "/kinopoisk/Cinema/{id}/Phone"
    const val CINEMA_SCHEDULE_URL = "/kinopoisk/Cinema/{id}/Schedule"
    const val CINEMA_REVIEW_URL = "/kinopoisk/Cinema/{cinemaId}/Review"
    const val CINEMA_ADD_REVIEW_URL = "/kinopoisk/User/Content/Cinema/{cinemaId}/Review"

    const val PLAYLIST_URL = "/kinopoisk/Playlist"

    const val SEARCH_PERSON_URL = "/api/v1/persons"
    const val STAFF_INF0_ID_URL = "/api/v1/staff/{id}"

    const val HISTORY_MOVIE_URL = "/kinopoisk/User/History/Movie"
    const val HISTORY_SEARCH_URL = "/kinopoisk/User/History/Search"

    const val USER_FAVORITE_MOVIE_URL = "/kinopoisk/User/Content/Favorite/Movie"
    const val USER_WATCH_LATER_URL = "/kinopoisk/User/Content/WatchLater/Movie"
    const val USER_FAVORITE_STAFF_URL = "/kinopoisk/User/Content/Favorite/Staff"
    const val USER_CINEMA_REVIEW_URL = "/kinopoisk/User/Content/Review/Cinema"

    const val AUTHORIZATION_URL = "/kinopoisk/Authorization"
    const val REGISTRATION_URL = "/kinopoisk/Registration"
    const val USER_INFO_URL = "/kinopoisk/User/Info"
    const val UPDATE_PASSWORD_URL = "/kinopoisk/User/Update/Password"
    const val UPDATE_PHOTO_URL = "/kinopoisk/User_Photo.jpg"
    const val ADMIN_PLAYLIST_URL = "/kinopoisk/Admin/Playlist"

    const val RIAK_AND_MORTY_CHARACTER_URL = "/api/character"
    const val RIAK_AND_MORTY_LOCATION_URL = "/api/location"
    const val RIAK_AND_MORTY_EPISODES_URL = "/api/episode"
    const val RIAK_AND_MORTY_CHARACTER_BY_ID_URL = "/api/character/{id}"
    const val RIAK_AND_MORTY_EPISODES_BY_ID_URL = "/api/episode/{id}"

    const val MARVEL_CHARACTERS_URL = "/v1/public/characters"
    const val MARVEL_COMICS_URL = "/v1/public/comics"
    const val MARVEL_COMIC_CHARACTERS_URL = "/v1/public/comics/{comicId}/characters "

    const val IMDb_FILM_WIKIPEDIA_URL = "/{lang}/API/Wikipedia/{apiKey}/{id}"
    const val IMDb_FILM_FAQ_URL = "/{lang}/API/FAQ/{apiKey}/{id}"
    const val IMDb_FILM_AWARDS_URL = "/{lang}/API/Awards/{apiKey}/{id}"
    const val IMDb_EXTERNAL_SITE_URL = "/{lang}/API/ExternalSites/{apiKey}/{id}"

    const val MOVIE_VIDEO_URL = "/kinopoisk/Shop/Movie/{kimoPoiskId}/Video"

    const val ROUTE_URL = "/v2/directions/{profile}"
}