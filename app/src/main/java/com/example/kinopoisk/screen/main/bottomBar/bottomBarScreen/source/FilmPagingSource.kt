package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.FilmItem
import java.lang.Exception

class FilmPagingSource (
    private val order:String,
    private val type:String,
    private val ratingFrom:Int,
    private val ratingTo:Int,
    private val yearFrom:Int,
    private val yearTo:Int,
    private val keyword:String,
    private val apiRepository: ApiRepository
):PagingSource<Int, FilmItem>() {
    override fun getRefreshKey(state: PagingState<Int, FilmItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmItem> {
        return try {
            val nextPage = params.key ?: 1
            val film = apiRepository.getFilm(
                order = order,
                type = type,
                ratingFrom = ratingFrom,
                ratingTo =ratingTo,
                yearFrom = yearFrom,
                yearTo = yearTo,
                keyword = keyword,
                page = nextPage
            ).body()!!.items
            LoadResult.Page(
                data = film,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}