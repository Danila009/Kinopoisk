package com.example.feature_films.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.movie.FilmItem
import com.example.core_network_domain.useCase.movie.GetFilmUseCase
import java.lang.Exception

class FilmPagingSource (
    private val genres:List<Int>,
    private val countries:List<Int>,
    private val order:String,
    private val type:String,
    private val ratingFrom:Int,
    private val ratingTo:Int,
    private val yearFrom:Int,
    private val yearTo:Int,
    private val keyword:String,
    private val getFilmUseCase: GetFilmUseCase
):PagingSource<Int, FilmItem>() {
    override fun getRefreshKey(state: PagingState<Int, FilmItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmItem> {
        return try {
            val nextPage = params.key ?: 1
            val film = getFilmUseCase.invoke(
                genres = genres,
                countries = countries,
                order = order,
                type = type,
                ratingFrom = ratingFrom,
                ratingTo =ratingTo,
                yearFrom = yearFrom,
                yearTo = yearTo,
                keyword = keyword,
                page = nextPage
            ).items
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